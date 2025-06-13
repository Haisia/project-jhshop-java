package com.haisia.shop.order.service.domain.service.listener;

import com.haisia.shop.common.domain.event.payload.OrderCreatedEventPayload;
import com.haisia.shop.common.domain.outbox.OutboxMessage;
import com.haisia.shop.common.domain.outbox.OutboxMessageFactory;
import com.haisia.shop.common.domain.ports.output.repository.OutboxMessageRepository;
import com.haisia.shop.order.service.domain.order.entity.OrderItem;
import com.haisia.shop.order.service.domain.order.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedEventListener {

  private final OutboxMessageFactory outboxMessageFactory;
  private final OutboxMessageRepository outboxMessageRepository;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void persistOutboxMessage(OrderCreatedEvent event) {
    OrderCreatedEventPayload payload = toPayload(event);
    OutboxMessage outboxMessage = outboxMessageFactory.create(payload);
    outboxMessageRepository.save(outboxMessage);
  }

  private OrderCreatedEventPayload toPayload(OrderCreatedEvent event) {
    return OrderCreatedEventPayload.builder()
      .sagaId(UUID.randomUUID())
      .aggregateId(event.getOrder().getId().getValue())
      .orderItems(event.getOrder().getOrderItems().stream().map(this::toPayload).collect(Collectors.toList()))
      .price(event.getOrder().getPrice().amount())
      .buyerUserAuthId(event.getOrder().getBuyer().getValue())
      .sellerUserAuthId(event.getOrder().getSeller().getValue())
      .build();
  }

  private OrderCreatedEventPayload.OrderItem toPayload(OrderItem item) {
    return OrderCreatedEventPayload.OrderItem.builder()
      .productId(item.getProductId().getValue())
      .quantity(item.getQuantity())
      .price(item.getPrice().amount())
      .subTotal(item.getSubTotal().amount())
      .build();
  }

}
