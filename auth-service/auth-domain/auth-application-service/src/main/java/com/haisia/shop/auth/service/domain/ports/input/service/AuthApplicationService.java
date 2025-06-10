package com.haisia.shop.auth.service.domain.ports.input.service;

import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.auth.service.domain.dto.register.RegisterUserResponse;

public interface AuthApplicationService {
  RegisterUserResponse registerUser(RegisterUserCommand command);
}
