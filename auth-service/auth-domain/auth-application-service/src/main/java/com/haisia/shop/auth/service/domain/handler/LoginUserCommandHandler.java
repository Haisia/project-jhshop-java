package com.haisia.shop.auth.service.domain.handler;

import com.haisia.shop.auth.service.domain.config.JwtTokenProvider;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserCommand;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserResponse;
import com.haisia.shop.auth.service.domain.handler.helper.RefreshTokenHelper;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserAuthRepository;
import com.haisia.shop.auth.service.domain.refreshtokenrecord.event.RefreshTokenGeneratedEvent;
import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import com.haisia.shop.auth.service.domain.userauth.exception.UserAuthDomainException;
import com.haisia.shop.auth.service.domain.userloginrecord.event.UserLoginSucceedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class LoginUserCommandHandler {

  private final JwtTokenProvider refreshTokenProvider;
  private final JwtTokenProvider accessTokenProvider;
  private final UserAuthRepository userAuthRepository;
  private final PasswordEncoder passwordEncoder;
  private final RefreshTokenHelper refreshTokenHelper;
  private final ApplicationEventPublisher eventPublisher;

  private static final String TEMP_IP_ADDRESS = "127.0.0.1";

  /*
   * 1. 로그인 기록 persist (이벤트 리스너에서 처리)
   * 2. LoginSuccessEvent 이벤트 발행
   * */
  @Transactional
  public LoginUserResponse loginUser(LoginUserCommand command) {
    UserAuth foundUserAuth = userAuthRepository.findByEmail(command.email())
      .orElseThrow(() -> new UserAuthDomainException("이메일 또는 패스워드가 일치하지 않습니다."));

    if (!passwordEncoder.matches(command.password(), foundUserAuth.getHashedPassword())) {
      throw new UserAuthDomainException("이메일 또는 패스워드가 일치하지 않습니다.");
    }

    String refreshToken = refreshTokenProvider.generate(foundUserAuth.getId(), foundUserAuth.getEmail());
    String accessToken = accessTokenProvider.generate(foundUserAuth.getId(), foundUserAuth.getEmail());

    refreshTokenHelper.initiateAndPersist(refreshToken, foundUserAuth.getId());

    publishLoginSucceedEvent(command, foundUserAuth);
    publishRefreshTokenGeneratedEvent(refreshToken);

    return new LoginUserResponse(refreshToken, accessToken);
  }

  private void publishLoginSucceedEvent(LoginUserCommand command, UserAuth foundUserAuth) {
    UserLoginSucceedEvent loginSucceedEvent = UserLoginSucceedEvent.builder()
      .userAuthId(foundUserAuth.getId())
      .email(command.email())
      .ipAddress(TEMP_IP_ADDRESS)
      .build();
    eventPublisher.publishEvent(loginSucceedEvent);
  }

  private void publishRefreshTokenGeneratedEvent(String refreshToken) {
    RefreshTokenGeneratedEvent refreshTokenGeneratedEvent = RefreshTokenGeneratedEvent.builder()
      .refreshToken(refreshToken)
      .build();
    eventPublisher.publishEvent(refreshTokenGeneratedEvent);
  }
}
