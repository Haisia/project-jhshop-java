package com.haisia.shop.auth.service.domain.mapper;

import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import org.springframework.stereotype.Component;

@Component
public class UserAuthDataMapper {

  public UserAuth registerUserCommandToUserAuth(RegisterUserCommand command, String hashedPassword) {
    return UserAuth.builder()
      .email(command.email())
      .hashedPassword(hashedPassword)
      .build();
  }

}
