package com.haisia.shop.order.service.domain.ports.output.repository;

import com.haisia.shop.common.domain.valueobject.id.PaymentId;
import com.haisia.shop.order.service.domain.payment.entity.Payment;

import java.util.Optional;

public interface PaymentRepository {

  Payment save(Payment payment);
  Optional<Payment> findById(PaymentId id);

}
