package com.haisia.shop.auth.service.client.http.feign.userservice.adapter;

import com.haisia.shop.auth.service.client.http.feign.userservice.UserServiceFeignClient;
import com.haisia.shop.auth.service.client.http.feign.userservice.dto.CreateUserProfileFeignCommand;
import com.haisia.shop.auth.service.client.http.feign.userservice.exception.UserServiceFeignException;
import com.haisia.shop.auth.service.client.http.feign.userservice.mapper.UserServiceFeignMapper;
import com.haisia.shop.auth.service.domain.ports.output.client.UserServiceClient;
import com.haisia.shop.auth.service.domain.userauth.event.UserAuthCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserServiceClientImpl implements UserServiceClient {

  private final UserServiceFeignClient client;
  private final UserServiceFeignMapper mapper;

  @Value("${feign.secret.key}")
  private String feignSecretKey;

  @Override
  public void createUserProfile(UserAuthCreatedEvent event) {
    CreateUserProfileFeignCommand command = mapper.toCreateUserProfileFeignCommand(event);
    ResponseEntity<?> response = client.createUserProfile(feignSecretKey, command);

    if (response.getStatusCode().isError()) {
      throw new UserServiceFeignException("UserProfile 생성에 실패하였습니다.");
    }
  }
}
