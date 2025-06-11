package com.haisia.shop.user.service.domain.ports.output.repository;

import com.haisia.shop.common.domain.event.payload.EventPayload;

public interface EventPayloadRepository {
  EventPayload save(EventPayload eventPayload);
}
