package com.haisia.shop.common.domain.event;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

public class OutboxMessage {
  private final UUID sagaId;
  private final UUID aggregateId;
  private final String aggregateType;
  private final String eventName;
  private final Instant createdAt;
  private final EventPayload payload;

  @Builder
  private OutboxMessage(
    UUID sagaId,
    UUID aggregateId,
    String aggregateType,
    String eventName,
    Instant createdAt,
    EventPayload payload
  ) {
    this.sagaId = sagaId;
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.eventName = eventName;
    this.createdAt = createdAt;
    this.payload = payload;
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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public EventPayload getPayload() {
    return payload;
  }
}
