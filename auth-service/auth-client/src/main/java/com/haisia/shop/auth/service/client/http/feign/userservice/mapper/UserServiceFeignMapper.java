package com.haisia.shop.auth.service.client.http.feign.userservice.mapper;

import com.haisia.shop.auth.service.client.http.feign.userservice.dto.CreateUserProfileFeignCommand;
import com.haisia.shop.auth.service.domain.userauth.event.UserAuthCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFeignMapper {

  public CreateUserProfileFeignCommand toCreateUserProfileFeignCommand(UserAuthCreatedEvent event) {
    return CreateUserProfileFeignCommand.builder()
      .userAuthId(event.getUserAuth().getId().getValue())
      .email(event.getUserAuth().getEmail())
      .address(event.getAddress())
      .phoneNumber(event.getPhoneNumber())
      .build();
  }

}
