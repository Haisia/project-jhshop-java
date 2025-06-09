package com.haisia.shop.common.domain.event;

import com.haisia.shop.common.domain.event.payload.EventPayload;

public interface DomainEvent<T> {
  EventPayload payload();
}
