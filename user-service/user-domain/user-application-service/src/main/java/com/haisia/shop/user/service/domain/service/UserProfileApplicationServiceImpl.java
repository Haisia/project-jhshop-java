package com.haisia.shop.user.service.domain.service;

import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileCommand;
import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileResponse;
import com.haisia.shop.user.service.domain.handler.UserProfileCreateCommandHandler;
import com.haisia.shop.user.service.domain.ports.input.application.UserProfileApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Validated
@Service
public class UserProfileApplicationServiceImpl implements UserProfileApplicationService {

  private final UserProfileCreateCommandHandler userProfileCreateCommandHandler;

  @Override
  public CreateUserProfileResponse createUserProfile(CreateUserProfileCommand command) {
    return userProfileCreateCommandHandler.createUserProfile(command);
  }
}
