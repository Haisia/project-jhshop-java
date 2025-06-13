package com.haisia.shop.user.service.domain.mapper;

import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileCommand;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDataMapper {

  public UserProfile createUserProfileCommandToUserProfile(CreateUserProfileCommand command) {
    return UserProfile.builder()
      .userAuthId(new UserAuthId(command.userAuthId()))
      .email(command.email())
      .address(command.address())
      .phoneNumber(command.phoneNumber())
      .build();
  }

}
