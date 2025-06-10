package com.haisia.shop.user.service.domain;

import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileResponse;
import com.haisia.shop.user.service.domain.handler.UserProfileCreateCommandHandler;
import com.haisia.shop.user.service.domain.ports.input.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Validated
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

  private final UserProfileCreateCommandHandler userProfileCreateCommandHandler;

  @Override
  public CreateUserProfileResponse createUserProfile(CreateUserProfileCommand command) {
    return userProfileCreateCommandHandler.createUserProfile(command);
  }
}
