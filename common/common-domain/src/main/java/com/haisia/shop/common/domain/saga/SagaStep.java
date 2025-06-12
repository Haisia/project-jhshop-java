package com.haisia.shop.common.domain.saga;

import com.haisia.shop.common.domain.event.payload.EventPayload;

public interface SagaStep<T extends EventPayload> {
  void process(T payload);

  void rollback(T payload);
}
