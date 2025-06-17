package com.haisia.shop.order.service.domain.service.application;

import com.haisia.shop.common.domain.event.DomainEvent;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.order.service.domain.dto.create.CreatePaymentCommand;
import com.haisia.shop.order.service.domain.mapper.PaymentDataMapper;
import com.haisia.shop.order.service.domain.order.entity.Order;
import com.haisia.shop.order.service.domain.order.event.OrderCreatedEvent;
import com.haisia.shop.order.service.domain.order.exception.OrderDomainException;
import com.haisia.shop.order.service.domain.order.exception.OrderNotFoundException;
import com.haisia.shop.order.service.domain.order.valuobject.OrderStatus;
import com.haisia.shop.order.service.domain.payment.PaymentDomainService;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import com.haisia.shop.order.service.domain.payment.event.PaymentCreatedEvent;
import com.haisia.shop.order.service.domain.ports.input.application.PaymentApplicationService;
import com.haisia.shop.order.service.domain.ports.output.repository.OrderRepository;
import com.haisia.shop.order.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

  private final PaymentDataMapper mapper;
  private final PaymentDomainService domainService;
  private final PaymentRepository repository;

  private final OrderRepository orderRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  @Override
  public void create(CreatePaymentCommand command, UserSession userSession) {
    Order foundOrder = orderRepository.findById(new OrderId(command.orderId()))
      .orElseThrow(() -> new OrderNotFoundException("Order 를 찾을 수 없습니다. OrderId: " + command.orderId()));

    if (!foundOrder.getBuyer().equals(new UserAuthId(UUID.fromString(userSession.userAuthId())))) {
      throw new OrderDomainException(String.format(
        "buyerId 가 기대값과 다릅니다. Order.buyer: %s, userSession.userAuthId: %s",
        foundOrder.getBuyer(),
        userSession.userAuthId()
      ));
    }

    if (foundOrder.getOrderStatus() != OrderStatus.PENDING) {
      throw new OrderDomainException(String.format("Order.orderStatus 가 기대값과 다릅니다. expect: %s, actual: %s",
        OrderStatus.PENDING,
        foundOrder.getOrderStatus()
        ));
    }

    Payment payment = mapper.toPayment(command);
    PaymentCreatedEvent paymentCreatedEvent = (PaymentCreatedEvent) domainService.validateAndInitiate(payment);
    repository.save(payment);

    eventPublisher.publishEvent(paymentCreatedEvent);
  }
}
