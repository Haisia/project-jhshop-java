package com.haisia.shop.user.service.domain.event;

import com.haisia.shop.common.domain.event.DomainEvent;
import com.haisia.shop.user.service.domain.entity.UserProfile;

import java.time.ZonedDateTime;

public abstract class UserProfileEvent implements DomainEvent {
  private final UserProfile userProfile;
  private final ZonedDateTime createdAt;

  public UserProfileEvent(UserProfile userProfile, ZonedDateTime createdAt) {
    this.userProfile = userProfile;
    this.createdAt = createdAt;
  }

  public UserProfile getUserProfile() {
    return userProfile;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

}
