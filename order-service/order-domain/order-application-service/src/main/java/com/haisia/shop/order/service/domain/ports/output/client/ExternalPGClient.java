package com.haisia.shop.order.service.domain.ports.output.client;

import com.haisia.shop.order.service.domain.payment.entity.Payment;

public interface ExternalPGClient {
  boolean requestPayment(Payment payment);
}
