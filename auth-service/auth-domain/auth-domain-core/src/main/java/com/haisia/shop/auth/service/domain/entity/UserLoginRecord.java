package com.haisia.shop.auth.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserLoginRecordId;
import lombok.Builder;

import java.time.Instant;

public class UserLoginRecord extends AggregateRoot<UserLoginRecordId> {
  private final UserAuthId userAuthId;
  private final String email;
  private final Instant succeedAt;
  private final String ipAddress;
  private boolean isFirstLoginOfDay;

  @Builder
  private UserLoginRecord(
    UserLoginRecordId id,
    UserAuthId userAuthId,
    String email,
    Instant succeedAt,
    String ipAddress,
    boolean isFirstLoginOfDay
  ) {
    super.setId(id);
    this.userAuthId = userAuthId;
    this.email = email;
    this.succeedAt = succeedAt;
    this.ipAddress = ipAddress;
    this.isFirstLoginOfDay = isFirstLoginOfDay;
  }

  public UserAuthId getUserAuthId() {
    return userAuthId;
  }

  public String getEmail() {
    return email;
  }

  public Instant getSucceedAt() {
    return succeedAt;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public boolean isFirstLoginOfDay() {
    return isFirstLoginOfDay;
  }

  public void setFirstLoginOfDay(boolean firstLoginOfDay) {
    isFirstLoginOfDay = firstLoginOfDay;
  }
}
