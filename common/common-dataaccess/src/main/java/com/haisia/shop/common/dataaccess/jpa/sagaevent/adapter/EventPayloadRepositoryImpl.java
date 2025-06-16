package com.haisia.shop.common.dataaccess.jpa.sagaevent.adapter;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.dataaccess.jpa.sagaevent.entity.SagaEventJpaEntity;
import com.haisia.shop.common.dataaccess.jpa.sagaevent.mapper.SagaEventDataaccessMapper;
import com.haisia.shop.common.dataaccess.jpa.sagaevent.repository.SagaEventJpaRepository;
import com.haisia.shop.common.domain.ports.output.repository.EventPayloadRepository;
import com.haisia.shop.common.domain.saga.SagaStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EventPayloadRepositoryImpl implements EventPayloadRepository {

  private final SagaEventJpaRepository repository;
  private final SagaEventDataaccessMapper mapper;

  @Override
  public EventPayload save(EventPayload eventPayload) {
    SagaEventJpaEntity jpaEntity = mapper.toJpaEntity(eventPayload);
    return mapper.toEventPayload(repository.save(jpaEntity));
  }

  @Override
  public Optional<EventPayload> findById(UUID sagaId) {
    return repository.findById(sagaId)
      .map(mapper::toEventPayload);
  }

  @Override
  public Map<UUID, EventPayload> saveAll(Map<UUID, EventPayload> eventPayloads) {
    List<SagaEventJpaEntity> list = eventPayloads.values().stream()
      .map(mapper::toJpaEntity)
      .toList();

    return repository.saveAll(list).stream()
      .map(mapper::toEventPayload)
      .collect(Collectors.toMap(EventPayload::getSagaId, event -> event));
  }

  @Override
  public Map<UUID, EventPayload> findAllByStatus(SagaStatus sagaStatus) {
    return repository.findAllByStatus(sagaStatus).stream()
      .map(mapper::toEventPayload)
      .collect(Collectors.toMap(EventPayload::getSagaId, event -> event));
  }
}
