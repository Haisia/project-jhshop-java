package com.haisia.shop.auth.service.domain.handler;

import com.haisia.shop.auth.service.domain.config.JwtTokenProvider;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserCommand;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserResponse;
import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.auth.service.domain.exception.UserAuthDomainException;
import com.haisia.shop.auth.service.domain.handler.helper.RefreshTokenHelper;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
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

  @Transactional
  public LoginUserResponse loginUser(LoginUserCommand command) {
    UserAuth foundUser =
      userAuthRepository.findByEmail(command.email())
        .orElseThrow(() -> new UserAuthDomainException("이메일 또는 패스워드가 일치하지 않습니다."));

    if (!passwordEncoder.matches(command.password(), foundUser.getHashedPassword())) {
      throw new UserAuthDomainException("이메일 또는 패스워드가 일치하지 않습니다.");
    }

    String refreshToken = refreshTokenProvider.generate(foundUser.getId(), foundUser.getEmail());
    String accessToken = accessTokenProvider.generate(foundUser.getId(), foundUser.getEmail());

    refreshTokenHelper.initiateAndPersist(refreshToken, foundUser.getId());

    return new LoginUserResponse(refreshToken, accessToken);
  }
}
