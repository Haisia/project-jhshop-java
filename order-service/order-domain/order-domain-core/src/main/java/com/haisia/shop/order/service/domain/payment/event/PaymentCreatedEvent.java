package com.haisia.shop.order.service.domain.payment.event;

import com.haisia.shop.order.service.domain.payment.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentCreatedEvent extends PaymentEvent{
  public PaymentCreatedEvent(Payment payment, ZonedDateTime createdAt) {
    super(payment, createdAt);
  }
}
