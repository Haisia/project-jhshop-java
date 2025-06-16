package com.haisia.shop.common.domain.outbox;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.saga.SagaAction;
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
  private final SagaAction action;

  @Builder
  private OutboxMessage(
    UUID sagaId,
    UUID aggregateId,
    String aggregateType,
    String eventName,
    LocalDateTime createdAt,
    EventPayload payload,
    SagaAction action
  ) {
    this.sagaId = sagaId;
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.eventName = eventName;
    this.createdAt = createdAt;
    this.payload = payload;
    this.action = action == null ? SagaAction.PROCESS : action;
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

  public SagaAction getAction() {
    return action;
  }
}
