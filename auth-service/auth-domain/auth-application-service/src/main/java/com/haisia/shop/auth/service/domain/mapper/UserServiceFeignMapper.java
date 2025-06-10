package com.haisia.shop.auth.service.domain.mapper;

import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserServiceFeignMapper {

  public CreateUserProfileCommand registerUserCommandToCreateUserProfileCommand(
    RegisterUserCommand command,
    UUID userAuthId
  ) {
    return CreateUserProfileCommand.builder()
      .userAuthId(userAuthId)
      .email(command.email())
      .address(command.address())
      .phoneNumber(command.phoneNumber())
      .build();
  }
}
