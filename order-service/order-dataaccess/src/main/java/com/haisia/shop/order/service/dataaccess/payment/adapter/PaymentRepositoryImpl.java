package com.haisia.shop.order.service.dataaccess.payment.adapter;

import com.haisia.shop.common.domain.valueobject.id.PaymentId;
import com.haisia.shop.order.service.dataaccess.payment.repository.PaymentJpaRepository;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import com.haisia.shop.order.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentJpaRepository repository;

  @Override
  public Payment save(Payment payment) {
    return repository.save(payment);
  }

  @Override
  public Optional<Payment> findById(PaymentId id) {
    return repository.findById(id);
  }
}
