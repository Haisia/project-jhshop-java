package com.haisia.shop.common.domain.event.payload;

import java.util.UUID;

public abstract class EventPayload {
  private final UUID sagaId;
  private final UUID aggregateId;
  private final String aggregateType;
  private final String eventName;

  protected EventPayload(UUID sagaId, UUID aggregateId, String aggregateType, String eventName) {
    this.sagaId = sagaId;
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.eventName = eventName;
  }

  public UUID getSagaId() {
    return sagaId;
  }

  public UUID getAggregateId() {
    return aggregateId;
  }

  public String getAggregateType() {
    return aggregateType;
  }

  public String getEventName() {
    return eventName;
  }
}
