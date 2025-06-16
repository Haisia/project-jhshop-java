package com.haisia.shop.order.service.domain.service.application;

import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.order.service.domain.order.OrderDomainService;
import com.haisia.shop.order.service.domain.dto.create.CreateOrderCommand;
import com.haisia.shop.order.service.domain.order.entity.Order;
import com.haisia.shop.order.service.domain.mapper.OrderDataMapper;
import com.haisia.shop.order.service.domain.order.event.OrderCreatedEvent;
import com.haisia.shop.order.service.domain.ports.input.application.OrderApplicationService;
import com.haisia.shop.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

  private final OrderRepository repository;
  private final OrderDataMapper mapper;
  private final OrderDomainService domainService;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  @Override
  public void create(CreateOrderCommand command, UserSession userSession) {
    Order createdOrder = mapper.toOrder(command, userSession);
    OrderCreatedEvent orderCreatedEvent = (OrderCreatedEvent) domainService.validateAndInitiate(createdOrder);
    repository.save(createdOrder);

    eventPublisher.publishEvent(orderCreatedEvent);
  }
}
