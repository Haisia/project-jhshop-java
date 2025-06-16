package com.haisia.shop.order.service.messaging.listener;

import com.haisia.shop.common.domain.event.payload.OrderCreatedEventPayload;
import com.haisia.shop.common.messaging.EventPayloadFactory;
import com.haisia.shop.order.service.domain.ports.input.message.OrderCreatedUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedMessageListener {

  private final OrderCreatedUsecase orderCreatedUsecase;
  private final EventPayloadFactory eventPayloadFactory;

  @Transactional
  @Bean
  public Consumer<Message<String>> processOrderCreated() {
    return message -> {
      OrderCreatedEventPayload payload = (OrderCreatedEventPayload) eventPayloadFactory.from(message);

      switch (payload.getAction()) {
        case PROCESS -> orderCreatedUsecase.process(payload);
        case ROLLBACK -> orderCreatedUsecase.rollback(payload);
      }
    };
  }
}