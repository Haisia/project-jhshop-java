package com.haisia.shop.order.service.domain.service.message;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.event.payload.OrderCreatedEventPayload;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.common.domain.ports.output.repository.EventPayloadRepository;
import com.haisia.shop.common.domain.saga.SagaStatus;
import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.order.service.domain.order.entity.Order;
import com.haisia.shop.order.service.domain.order.exception.OrderNotFoundException;
import com.haisia.shop.order.service.domain.order.valuobject.OrderStatus;
import com.haisia.shop.order.service.domain.ports.input.message.OrderCreatedUsecase;
import com.haisia.shop.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedMessageService implements OrderCreatedUsecase {

  private final OrderRepository orderRepository;
  private final EventPayloadRepository eventPayloadRepository;

  @Override
  public void process(OrderCreatedEventPayload payload) {
    log.debug("OrderCreatedEventPayload 의 process 메세지를 수신했지만, 아무것도 하지 않음.");
  }

  @Transactional
  @Override
  public void rollback(OrderCreatedEventPayload payload) {
    OrderId orderId = new OrderId(payload.getAggregateId());
    Order order = orderRepository.findById(orderId)
      .orElseThrow(
        () -> new OrderNotFoundException("Order 를 찾을 수 없습니다. orderId = " + orderId.getValue().toString())
      );
    order.addFailureMessages(payload.getFailureMessages());
    order.changeStatus(OrderStatus.CANCELLED);

    EventPayload eventPayload = eventPayloadRepository.findById(payload.getSagaId())
      .orElseThrow(
        () -> new DomainException("EventPayload 를 찾을 수 없습니다. sagaId = " + payload.getSagaId().toString())
      );
    eventPayload.setSagaStatus(SagaStatus.COMPENSATED);
    eventPayloadRepository.save(eventPayload);
  }
}
