package com.haisia.shop.common.domain.event;

import java.time.ZonedDateTime;

public abstract class DomainEvent {
  private final ZonedDateTime createdAt;

  protected DomainEvent(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }
}
