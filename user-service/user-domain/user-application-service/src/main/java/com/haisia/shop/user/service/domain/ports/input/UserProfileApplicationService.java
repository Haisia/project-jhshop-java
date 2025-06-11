package com.haisia.shop.user.service.domain.ports.input;

import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileResponse;
import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;

public interface UserProfileApplicationService {
  CreateUserProfileResponse createUserProfile(CreateUserProfileCommand command);
}
