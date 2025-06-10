package com.haisia.shop.user.service.domain.ports.input;

import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileResponse;

public interface UserApplicationService {
  CreateUserProfileResponse createUserProfile(CreateUserProfileCommand command);
}
