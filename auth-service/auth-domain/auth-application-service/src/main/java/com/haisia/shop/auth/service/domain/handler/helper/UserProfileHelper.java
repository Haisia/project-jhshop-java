package com.haisia.shop.auth.service.domain.handler.helper;

import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.auth.service.domain.mapper.UserServiceFeignMapper;
import com.haisia.shop.auth.service.domain.ports.output.client.feign.UserServiceClient;
import com.haisia.shop.auth.service.domain.userauth.exception.UserAuthDomainException;
import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserProfileHelper {

  private final UserServiceClient userServiceClient;
  private final UserServiceFeignMapper userServiceFeignMapper;

  @Value("${feign.secret.key}")
  private String feignSecretKey;

  public void createUserProfile(RegisterUserCommand command, UUID userAuthId) {
    CreateUserProfileCommand createUserProfileCommand =
      userServiceFeignMapper.registerUserCommandToCreateUserProfileCommand(command, userAuthId);

    ResponseEntity<?> userProfileFeignResult =
      userServiceClient.createUserProfile(feignSecretKey, createUserProfileCommand);

    if (userProfileFeignResult.getStatusCode().isError()) {
      throw new UserAuthDomainException("UserProfile 생성에 실패하였습니다.");
    }
  }
}
