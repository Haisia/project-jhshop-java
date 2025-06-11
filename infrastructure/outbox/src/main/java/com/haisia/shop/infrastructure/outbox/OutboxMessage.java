package com.haisia.shop.infrastructure.outbox;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class OutboxMessage {
  private final UUID sagaId;
  private final UUID aggregateId;
  private final String aggregateType;
  private final String eventName;
  private final LocalDateTime createdAt;
  private final EventPayload payload;

  @Builder
  private OutboxMessage(
    UUID sagaId,
    UUID aggregateId,
    String aggregateType,
    String eventName,
    LocalDateTime createdAt,
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public EventPayload getPayload() {
    return payload;
  }
}
