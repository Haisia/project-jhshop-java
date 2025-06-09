package com.haisia.shop.auth.service.domain.outbox;

import com.haisia.shop.infrastructure.outbox.OutboxStatus;
import com.haisia.shop.infrastructure.saga.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OutboxMessage {
  private UUID id;
  private UUID sagaId;
  private String payload;
  private SagaStatus sagaStatus;
  private OutboxStatus outboxStatus;
  private ZonedDateTime createdAt;
  private ZonedDateTime processedAt;
  private int version;

  public void setProcessedAt(ZonedDateTime processedAt) {
    this.processedAt = processedAt;
  }

  public void setSagaStatus(SagaStatus sagaStatus) {
    this.sagaStatus = sagaStatus;
  }

  public void setOutboxStatus(OutboxStatus outboxStatus) {
    this.outboxStatus = outboxStatus;
  }
}
