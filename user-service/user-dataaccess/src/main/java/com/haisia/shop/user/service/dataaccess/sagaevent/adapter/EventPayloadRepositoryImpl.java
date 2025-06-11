package com.haisia.shop.user.service.dataaccess.sagaevent.adapter;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.user.service.dataaccess.sagaevent.entity.SagaEventJpaEntity;
import com.haisia.shop.user.service.dataaccess.sagaevent.mapper.SagaEventDataaccessMapper;
import com.haisia.shop.user.service.dataaccess.sagaevent.repository.SagaEventJpaRepository;
import com.haisia.shop.user.service.domain.ports.output.repository.EventPayloadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
