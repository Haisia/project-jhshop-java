package com.haisia.shop.auth.service.domain.handler;

import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthResponse;
import com.haisia.shop.auth.service.domain.event.UserAuthCreatedEvent;
import com.haisia.shop.auth.service.domain.handler.helper.UserAuthCreateHelper;
import com.haisia.shop.auth.service.domain.mapper.UserAuthDataMapper;
import com.haisia.shop.auth.service.domain.outbox.OutboxHelper;
import com.haisia.shop.infrastructure.outbox.OutboxStatus;
import com.haisia.shop.infrastructure.saga.SagaStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserAuthCreateCommandHandler {

  private final UserAuthCreateHelper createHelper;
  private final UserAuthDataMapper mapper;
  private final OutboxHelper outboxHelper;

  @Transactional
  public CreateUserAuthResponse createUserAuth(CreateUserAuthCommand command) {
    UserAuthCreatedEvent event = createHelper.hashPasswordAndPersist(command);

    outboxHelper.saveOutboxMessage(event.payload(), SagaStatus.STARTED, OutboxStatus.STARTED);

    CreateUserAuthResponse response = CreateUserAuthResponse.from(event.getUserAuth());

    return response;
  }

}
