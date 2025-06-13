package com.haisia.shop.auth.service.domain.ports.input.service;

import com.haisia.shop.auth.service.domain.dto.login.LoginUserCommand;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserResponse;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenCommand;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenResponse;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthResponse;
import com.haisia.shop.auth.service.domain.dto.update.UpdatePasswordCommand;
import com.haisia.shop.auth.service.domain.dto.validate.ValidateAccessTokenResponse;
import com.haisia.shop.common.domain.valueobject.UserSession;

public interface AuthApplicationService {
  CreateUserAuthResponse createUserAuth(CreateUserAuthCommand command);

  LoginUserResponse loginUser(LoginUserCommand command);

  void updatePassword(UpdatePasswordCommand command, UserSession userSession);

  RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenCommand command);

  ValidateAccessTokenResponse validateAccessToken(String accessToken);
}
