package com.haisia.shop.infrastructure.outbox;

import com.haisia.shop.common.domain.event.payload.EventPayload;

import java.util.UUID;

public class OutboxMessageFactory {

  public OutboxMessage create(EventPayload payload) {
    return OutboxMessage.builder()
      .sagaId(UUID.randomUUID())
      .aggregateId(payload.getAggregateId())
      .aggregateType(payload.getAggregateType())
      .eventName(payload.getEventName())
      .payload(payload)
      .build();
  }
}
