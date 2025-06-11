package com.haisia.shop.user.service.domain.event;

import com.haisia.shop.common.domain.event.DomainEvent;
import com.haisia.shop.user.service.domain.entity.UserProfile;

import java.time.ZonedDateTime;

public abstract class UserProfileEvent extends DomainEvent {
  private final UserProfile userProfile;

  public UserProfileEvent(UserProfile userProfile, ZonedDateTime createdAt) {
    super(createdAt);
    this.userProfile = userProfile;
  }

  public UserProfile getUserProfile() {
    return userProfile;
  }
}
