package com.haisia.shop.common.domain.outbox;

import com.haisia.shop.common.domain.event.payload.EventPayload;

public class OutboxMessageFactory {

  public OutboxMessage create(EventPayload payload) {
    return OutboxMessage.builder()
      .sagaId(payload.getSagaId())
      .aggregateId(payload.getAggregateId())
      .aggregateType(payload.getAggregateType())
      .eventName(payload.getEventName())
      .action(payload.getAction())
      .payload(payload)
      .build();
  }
}
