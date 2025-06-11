package com.haisia.shop.common.domain.event.payload;

import com.haisia.shop.common.domain.saga.SagaStatus;
import lombok.Builder;

import java.util.UUID;

public class EventPayload {
  private final UUID sagaId;
  private final UUID aggregateId;
  private final String aggregateType;
  private final String eventName;

  private SagaStatus sagaStatus = SagaStatus.STARTED;

  public EventPayload(UUID sagaId, UUID aggregateId, String aggregateType, String eventName, SagaStatus sagaStatus) {
    this.sagaId = sagaId;
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.eventName = eventName;
    if (sagaStatus != null) {
      this.sagaStatus = sagaStatus;
    }
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

  public SagaStatus getSagaStatus() {
    return sagaStatus;
  }

  public void setSagaStatus(SagaStatus sagaStatus) {
    this.sagaStatus = sagaStatus;
  }
}
