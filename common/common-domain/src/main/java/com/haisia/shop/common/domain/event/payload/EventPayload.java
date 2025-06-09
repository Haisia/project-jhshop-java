package com.haisia.shop.common.domain.event.payload;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface EventPayload {
  UUID sagaId();
  ZonedDateTime createdAt();
}
