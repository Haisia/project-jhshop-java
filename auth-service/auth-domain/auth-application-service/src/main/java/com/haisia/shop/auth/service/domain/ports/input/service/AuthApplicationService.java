package com.haisia.shop.auth.service.domain.ports.input.service;

import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthResponse;

public interface AuthApplicationService {
  CreateUserAuthResponse createUserAuth(CreateUserAuthCommand command);
}
