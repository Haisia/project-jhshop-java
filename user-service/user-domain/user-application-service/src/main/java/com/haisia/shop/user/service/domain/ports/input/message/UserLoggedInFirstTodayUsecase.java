package com.haisia.shop.user.service.domain.ports.input.message;

import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import com.haisia.shop.common.domain.saga.SagaStep;

public interface UserLoggedInFirstTodayUsecase extends SagaStep<UserLoggedInFirstTodayEventPayload> {
}
