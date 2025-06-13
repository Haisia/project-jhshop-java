package com.haisia.shop.auth.service.domain.mapper;

import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import org.springframework.stereotype.Component;

@Component
public class UserAuthDataMapper {

  public UserAuth registerUserCommandToUserAuth(CreateUserAuthCommand command, String hashedPassword) {
    return UserAuth.builder()
      .email(command.email())
      .hashedPassword(hashedPassword)
      .build();
  }

}
