package com.haisia.shop.auth.service.domain;

import com.haisia.shop.auth.service.domain.dto.login.LoginUserCommand;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserResponse;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenCommand;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenResponse;
import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.auth.service.domain.dto.register.RegisterUserResponse;
import com.haisia.shop.auth.service.domain.handler.LoginUserCommandHandler;
import com.haisia.shop.auth.service.domain.handler.RefreshAccessTokenCommandHandler;
import com.haisia.shop.auth.service.domain.handler.UserAuthCreateCommandHandler;
import com.haisia.shop.auth.service.domain.ports.input.service.AuthApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Validated
@Service
public class AuthApplicationServiceImpl implements AuthApplicationService {

  private final UserAuthCreateCommandHandler userAuthCreateCommandHandler;
  private final LoginUserCommandHandler loginUserCommandHandler;
  private final RefreshAccessTokenCommandHandler refreshAccessTokenCommandHandler;

  @Override
  public RegisterUserResponse registerUser(RegisterUserCommand command) {
    return userAuthCreateCommandHandler.registerUser(command);
  }

  @Override
  public LoginUserResponse loginUser(LoginUserCommand command) {
    return loginUserCommandHandler.loginUser(command);
  }

  @Override
  public RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenCommand command) {
    return refreshAccessTokenCommandHandler.refreshAccessToken(command);
  }
}
