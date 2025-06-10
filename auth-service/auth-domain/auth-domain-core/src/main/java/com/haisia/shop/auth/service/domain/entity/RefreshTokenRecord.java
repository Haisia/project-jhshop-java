package com.haisia.shop.auth.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.id.RefreshTokenRecordId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.Builder;

import java.util.UUID;

public class RefreshTokenRecord extends AggregateRoot<RefreshTokenRecordId> {
  private final String token;
  private final UserAuthId userAuthId;
  private final int refreshCount;

  private boolean available;

  public void initialize() {
    setId(new RefreshTokenRecordId(UUID.randomUUID()));
  }

  public String getToken() {
    return token;
  }

  public UserAuthId getUserAuthId() {
    return userAuthId;
  }

  public int getRefreshCount() {
    return refreshCount;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  @Builder
  private RefreshTokenRecord(RefreshTokenRecordId id, String token, UserAuthId userAuthId, int refreshCount, boolean available) {
    super.setId(id);
    this.token = token;
    this.userAuthId = userAuthId;
    this.refreshCount = refreshCount;
    this.available = available;
  }
}
