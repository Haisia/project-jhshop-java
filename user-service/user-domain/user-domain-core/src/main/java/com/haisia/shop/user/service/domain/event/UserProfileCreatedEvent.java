package com.haisia.shop.user.service.domain.event;

import com.haisia.shop.user.service.domain.entity.UserProfile;

import java.time.ZonedDateTime;

public class UserProfileCreatedEvent extends UserProfileEvent {
  public UserProfileCreatedEvent(UserProfile userProfile, ZonedDateTime createdAt) {
    super(userProfile, createdAt);
  }
}
