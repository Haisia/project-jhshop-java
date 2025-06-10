package com.haisia.shop.auth.service.domain.event;

import com.haisia.shop.common.domain.event.DomainEvent;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.Builder;

import java.time.ZonedDateTime;

public class LoginSuccessEvent extends DomainEvent {
  private final UserAuthId userAuthId;
  private final String email;
  private final String ipAddress;

  @Builder
  public LoginSuccessEvent(
    UserAuthId userAuthId,
    String email,
    String ipAddress,
    ZonedDateTime createdAt
    ) {
    super(createdAt);
    this.userAuthId = userAuthId;
    this.email = email;
    this.ipAddress = ipAddress;
  }

  public UserAuthId getUserAuthId() {
    return userAuthId;
  }

  public String getEmail() {
    return email;
  }

  public String getIpAddress() {
    return ipAddress;
  }
}
