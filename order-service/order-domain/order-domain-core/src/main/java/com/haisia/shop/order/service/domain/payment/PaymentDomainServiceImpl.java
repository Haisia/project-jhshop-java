package com.haisia.shop.order.service.domain.payment;

import com.haisia.shop.common.domain.utile.CustomTimeFactory;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import com.haisia.shop.order.service.domain.payment.entity.PaymentInitializer;
import com.haisia.shop.order.service.domain.payment.event.PaymentCreatedEvent;
import com.haisia.shop.order.service.domain.payment.exception.PaymentDomainException;

public class PaymentDomainServiceImpl implements PaymentDomainService {
  @Override
  public PaymentCreatedEvent validateAndInitiate(Payment domain) {
    PaymentInitializer paymentInitializer = new PaymentInitializer(domain, PaymentDomainException::new);
    paymentInitializer.validateAndInitialize();
    return new PaymentCreatedEvent(domain, CustomTimeFactory.now());
  }
}
