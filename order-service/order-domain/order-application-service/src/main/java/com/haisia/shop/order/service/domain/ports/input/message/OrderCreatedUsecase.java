package com.haisia.shop.order.service.domain.ports.input.message;

import com.haisia.shop.common.domain.event.payload.OrderCreatedEventPayload;
import com.haisia.shop.common.domain.saga.SagaStep;

public interface OrderCreatedUsecase extends SagaStep<OrderCreatedEventPayload> {
}
