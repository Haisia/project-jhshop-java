package com.haisia.shop.common.application.saga;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.outbox.OutboxMessage;
import com.haisia.shop.common.domain.ports.output.repository.EventPayloadRepository;
import com.haisia.shop.common.domain.ports.output.repository.OutboxMessageRepository;
import com.haisia.shop.common.domain.saga.SagaStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SagaCompensateScheduler {

  private final EventPayloadRepository eventPayloadRepository;
  private final OutboxMessageRepository outboxMessageRepository;

  @Transactional
  @Scheduled(
    fixedDelayString = "${saga.compensate.scheduler.fixed-delay-string}",
    initialDelayString = "${saga.compensate.scheduler.initial-delay-string}"
  )
  public void processSagaCompensate() {
    Map<UUID, EventPayload> allByStatus = eventPayloadRepository.findAllByStatus(SagaStatus.COMPENSATING);

    allByStatus.values()
      .forEach(eventPayload -> {
        outboxMessageRepository.save(eventPayloadToOutboxMessage(eventPayload));
        eventPayload.setSagaStatus(SagaStatus.COMPENSATED);
      });

    eventPayloadRepository.saveAll(allByStatus);
  }

  private OutboxMessage eventPayloadToOutboxMessage(EventPayload eventPayload) {
    return OutboxMessage.builder()
      .sagaId(eventPayload.getSagaId())
      .aggregateId(eventPayload.getAggregateId())
      .aggregateType(eventPayload.getAggregateType())
      .eventName(eventPayload.getEventName())
      .action(eventPayload.getAction())
      .payload(eventPayload)
      .build();
  }
}
