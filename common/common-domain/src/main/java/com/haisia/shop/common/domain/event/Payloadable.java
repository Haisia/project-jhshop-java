package com.haisia.shop.common.domain.event;

import com.haisia.shop.common.domain.event.payload.EventPayload;

import java.util.UUID;

public interface Payloadable {
  EventPayload payload(UUID sagaId);
}
