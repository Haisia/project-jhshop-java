package com.haisia.shop.order.service.domain.payment.event;

import com.haisia.shop.common.domain.event.DomainEvent;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public abstract class PaymentEvent extends DomainEvent {

  private final Payment payment;

  protected PaymentEvent(Payment payment, ZonedDateTime createdAt) {
    super(createdAt);
    this.payment = payment;
  }
}
