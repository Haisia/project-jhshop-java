package com.haisia.shop.auth.service.domain.outbox;

import com.haisia.shop.auth.service.domain.ports.output.repository.OutboxRepository;
import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.infrastructure.outbox.OutboxStatus;
import com.haisia.shop.infrastructure.saga.SagaStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class OutboxHelper {

  private final OutboxRepository repository;

  @Transactional
  public void saveOutboxMessage(EventPayload payload, SagaStatus sagaStatus, OutboxStatus outboxStatus) {
    OutboxMessage message = OutboxMessage.builder()
      .id(UUID.randomUUID())
      .sagaId(payload.sagaId())
      .payload(payload.toString())
      .sagaStatus(sagaStatus)
      .outboxStatus(outboxStatus)
      .createdAt(payload.createdAt())
      .build();

    repository.save(message)
      .orElseThrow(() -> new DomainException("OutboxMessage 를 저장 할 수 없습니다. id:" + message.getId().toString()));

    log.info("OutboxMessage 를 저장했습니다. id: {}", message.getId().toString());
  }
}
