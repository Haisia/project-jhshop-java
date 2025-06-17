package com.haisia.shop.order.service.domain.service.listener;

import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.order.service.domain.order.OrderDomainService;
import com.haisia.shop.order.service.domain.order.entity.Order;
import com.haisia.shop.order.service.domain.order.exception.OrderDomainException;
import com.haisia.shop.order.service.domain.order.exception.OrderNotFoundException;
import com.haisia.shop.order.service.domain.order.valuobject.OrderStatus;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import com.haisia.shop.order.service.domain.payment.event.PaymentCreatedEvent;
import com.haisia.shop.order.service.domain.payment.exception.PaymentDomainException;
import com.haisia.shop.order.service.domain.payment.valueobject.PaymentStatus;
import com.haisia.shop.order.service.domain.ports.output.client.ExternalPGClient;
import com.haisia.shop.order.service.domain.ports.output.repository.OrderRepository;
import com.haisia.shop.order.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentCreatedEventListener {

  private final ExternalPGClient externalPGClient;
  private final PaymentRepository repository;
  private final OrderDomainService orderDomainService;

  private final OrderRepository orderRepository;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void requestPaymentToExternalPGService(PaymentCreatedEvent event) {
    Payment payment = event.getPayment();
    Order foundOrder = orderRepository.findById(new OrderId(payment.getOrderId().getValue()))
      .orElseThrow(() -> new OrderNotFoundException(
        "Order 를 찾을 수 없습니다. OrderId: " +
          payment.getOrderId().getValue())
      );
    if (foundOrder.getOrderStatus() != OrderStatus.PENDING) {
      throw new OrderDomainException(String.format(
        "Order.orderStatus 가 기대값과 다릅니다. expect: %s, actual: %s",
        OrderStatus.PENDING,
        foundOrder.getOrderStatus()
        ));
    }

    if(payment.getStatus() != PaymentStatus.PENDING) {
      throw new PaymentDomainException(String.format(
        "Payment.paymentStatus 가 기대값과 다릅니다. expect: %s, actual: %s",
        PaymentStatus.PENDING,
        payment.getStatus()));
    }

    boolean paymentResult = externalPGClient.requestPayment(payment);

    if (!paymentResult) {
      payment.changeStatus(PaymentStatus.CANCELLED);
    } else {
      payment.changeStatus(PaymentStatus.PAID);
      orderDomainService.pay(foundOrder);
    }

    repository.save(payment);
  }

}
