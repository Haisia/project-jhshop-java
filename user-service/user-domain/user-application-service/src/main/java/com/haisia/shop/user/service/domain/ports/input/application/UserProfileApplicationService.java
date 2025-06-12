package com.haisia.shop.user.service.domain.ports.input.application;

import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileResponse;

public interface UserProfileApplicationService {
  CreateUserProfileResponse createUserProfile(CreateUserProfileCommand command);
}
