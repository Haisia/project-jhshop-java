package com.haisia.shop.auth.service.domain.event;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.common.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public abstract class UserAuthEvent extends DomainEvent {
  private final UserAuth userAuth;

  public UserAuthEvent(UserAuth userAuth, ZonedDateTime createdAt) {
    super(createdAt);
    this.userAuth = userAuth;
  }

  public UserAuth getUserAuth() {
    return userAuth;
  }
}
