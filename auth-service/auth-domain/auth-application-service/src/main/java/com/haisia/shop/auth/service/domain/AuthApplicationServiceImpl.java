package com.haisia.shop.auth.service.domain;

import com.haisia.shop.auth.service.domain.config.JwtTokenProvider;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserCommand;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserResponse;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenCommand;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenResponse;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthResponse;
import com.haisia.shop.auth.service.domain.dto.update.UpdatePasswordCommand;
import com.haisia.shop.auth.service.domain.dto.validate.ValidateAccessTokenResponse;
import com.haisia.shop.auth.service.domain.handler.LoginUserCommandHandler;
import com.haisia.shop.auth.service.domain.handler.RefreshAccessTokenCommandHandler;
import com.haisia.shop.auth.service.domain.handler.UpdatePasswordCommandHandler;
import com.haisia.shop.auth.service.domain.handler.CreateUserAuthCommandHandler;
import com.haisia.shop.auth.service.domain.ports.input.service.AuthApplicationService;
import com.haisia.shop.common.domain.valueobject.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Validated
@Service
public class AuthApplicationServiceImpl implements AuthApplicationService {

  private final CreateUserAuthCommandHandler createUserAuthCommandHandler;
  private final LoginUserCommandHandler loginUserCommandHandler;
  private final RefreshAccessTokenCommandHandler refreshAccessTokenCommandHandler;
  private final UpdatePasswordCommandHandler updatePasswordCommandHandler;

  private final JwtTokenProvider accessTokenProvider;

  @Override
  public CreateUserAuthResponse createUserAuth(CreateUserAuthCommand command) {
    return createUserAuthCommandHandler.create(command);
  }

  @Override
  public LoginUserResponse loginUser(LoginUserCommand command) {
    return loginUserCommandHandler.loginUser(command);
  }

  @Override
  public void updatePassword(UpdatePasswordCommand command, UserSession userSession) {
    updatePasswordCommandHandler.updatePassword(command, userSession);
  }

  @Override
  public RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenCommand command) {
    return refreshAccessTokenCommandHandler.refreshAccessToken(command);
  }

  @Override
  public ValidateAccessTokenResponse validateAccessToken(String accessToken) {
    if (!accessTokenProvider.validate(accessToken)) throw new RuntimeException("Invalid access token");
    return new ValidateAccessTokenResponse(accessTokenProvider.getUserAuthId(accessToken).getValue());
  }

}
