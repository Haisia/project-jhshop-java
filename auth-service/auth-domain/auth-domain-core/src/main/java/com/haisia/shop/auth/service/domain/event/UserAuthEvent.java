package com.haisia.shop.auth.service.domain.event;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.common.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public abstract class UserAuthEvent implements DomainEvent<UserAuth> {
  private final UserAuth userAuth;
  private final ZonedDateTime createdAt;

  public UserAuthEvent(UserAuth userAuth, ZonedDateTime createdAt) {
    this.userAuth = userAuth;
    this.createdAt = createdAt;
  }

  public UserAuth getUserAuth() {
    return userAuth;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

}
