package com.haisia.shop.common.domain.saga;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.ports.output.repository.EventPayloadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class EventPayloadUpdater {

  private final EventPayloadRepository eventPayloadRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public EventPayload update(EventPayload eventPayload) {
    return eventPayloadRepository.save(eventPayload);
  }

}
