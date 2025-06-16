package com.haisia.shop.order.service.domain.payment.entity;

import com.haisia.shop.common.domain.AggregateRootInitializer;
import com.haisia.shop.order.service.domain.payment.exception.PaymentDomainException;

import java.util.Map;
import java.util.function.Function;

public class PaymentInitializer extends AggregateRootInitializer<Payment, PaymentDomainException> {

  public PaymentInitializer(Payment target, Function<String, PaymentDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateOrderId(errors);
    validatePrice(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
  }

  private void validateOrderId(Map<String, String> errors) {
    super.validateBaseId(errors, target.getOrderId(), "orderId");
  }

  private void validatePrice(Map<String, String> errors) {
    if (target.getPrice() == null || target.getPrice().getAmount() == null) {
      errors.put("price", NULL);
      return;
    }

    if (target.getPrice().isLessThanZero()) {
      errors.put("price", "payment.price 는 0 보다 커야합니다. payment.price: " + target.getPrice().getAmount());
    }
  }
}
