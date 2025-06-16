package com.haisia.shop.common.domain.ports.output.repository;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.saga.SagaStatus;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface EventPayloadRepository {
  EventPayload save(EventPayload eventPayload);

  Map<UUID, EventPayload> saveAll(Map<UUID, EventPayload> eventPayloads);
  Map<UUID,EventPayload> findAllByStatus(SagaStatus sagaStatus);
}
