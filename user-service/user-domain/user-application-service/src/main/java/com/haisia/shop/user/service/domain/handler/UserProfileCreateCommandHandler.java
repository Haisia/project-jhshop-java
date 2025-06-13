package com.haisia.shop.user.service.domain.handler;

import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileCommand;
import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileResponse;
import com.haisia.shop.user.service.domain.event.UserProfileCreatedEvent;
import com.haisia.shop.user.service.domain.handler.helper.UserProfileCreateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProfileCreateCommandHandler {

  private final UserProfileCreateHelper helper;

  public CreateUserProfileResponse createUserProfile(CreateUserProfileCommand command) {
    UserProfileCreatedEvent event = helper.persist(command);
    return new CreateUserProfileResponse(event.getUserProfile().getId().getValue());
  }
}
