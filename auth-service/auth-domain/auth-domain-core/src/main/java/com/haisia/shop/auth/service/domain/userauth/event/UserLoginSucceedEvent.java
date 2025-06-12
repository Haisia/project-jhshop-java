package com.haisia.shop.auth.service.domain.userauth.event;

import com.haisia.shop.common.domain.event.DomainEvent;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.Builder;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.haisia.shop.common.domain.DomainConstants.UTC;

public class UserLoginSucceedEvent extends DomainEvent {
  private final UserAuthId userAuthId;
  private final String email;
  private final String ipAddress;

  @Builder
  private UserLoginSucceedEvent(
    UserAuthId userAuthId,
    String email,
    String ipAddress
    ) {
    super(ZonedDateTime.now(ZoneId.of(UTC)));
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
