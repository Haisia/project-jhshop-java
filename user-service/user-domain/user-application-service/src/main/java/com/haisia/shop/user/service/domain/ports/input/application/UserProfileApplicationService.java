package com.haisia.shop.user.service.domain.ports.input.application;

import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileCommand;
import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileResponse;

public interface UserProfileApplicationService {
  CreateUserProfileResponse createUserProfile(CreateUserProfileCommand command);
}
