package com.haisia.shop.auth.service.domain;

import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthResponse;
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

  @Override
  public CreateUserAuthResponse createUserAuth(CreateUserAuthCommand command) {
    return userAuthCreateCommandHandler.createUserAuth(command);
  }
}
