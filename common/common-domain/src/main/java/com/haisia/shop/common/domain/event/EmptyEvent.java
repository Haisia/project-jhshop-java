package com.haisia.shop.common.domain.event;

import java.time.ZonedDateTime;

public final class EmptyEvent extends DomainEvent {

  public static final EmptyEvent INSTANCE = new EmptyEvent(ZonedDateTime.now());

  EmptyEvent(ZonedDateTime createdAt) {
    super(createdAt);
  }
}
