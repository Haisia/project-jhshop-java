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
    validateCardHolderName(errors);
    validateCardHolderEmail(errors);
    validateCardNumber(errors);
    validateExpiryDate(errors);
    validateCvc(errors);
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

  private void validateCardHolderName(Map<String, String> errors) {
    if (target.getCardHolderName() == null || target.getCardHolderName().isEmpty()) {
      errors.put("cardHolderName", NULL);
      return;
    }
  }

  private void validateCardHolderEmail(Map<String, String> errors) {
    super.validateEmail(errors, target.getCardHolderEmail(), "cardHolderEmail");
  }

  private void validateCardNumber(Map<String, String> errors) {
    String[] split = target.getCardNumber().split("-");
    if (split.length != 4) {
      errors.put(
        "cardNumber",
        "Payment.cardNumber 의 형식이 올바르지 않습니다. Payment.cardNumber: " + target.getCardNumber()
      );
      return;
    }

    for (String number : split) {
      if (number.length() != 4) {
        errors.put(
          "cardNumber",
          "Payment.cardNumber 의 형식이 올바르지 않습니다. Payment.cardNumber: " + target.getCardNumber()
        );
        break;
      }
    }
  }

  private void validateExpiryDate(Map<String, String> errors) {
    if (target.getExpiryDate() == null || target.getExpiryDate().isEmpty()) {
      errors.put("expiryDate", NULL);
      return;
    }

    String[] split = target.getExpiryDate().split("/");
    if (split.length != 2) {
      errors.put("expiryDate", "payment.expiryDate 의 형식이 올바르지 않습니다. expect: MM/YY actual: " + target.getExpiryDate());
      return;
    }

    for (String date : split) {
      try {
        Integer.parseInt(date);
      } catch(Exception e) {
        errors.put("expiryDate", "payment.expiryDate 의 형식이 올바르지 않습니다. expect: MM/YY actual: " + target.getExpiryDate());
        return;
      }
    }
  }

  private void validateCvc(Map<String, String> errors) {
    if (target.getCvc() == null) {
      errors.put("cvc", NULL);
      return;
    }

    if (target.getCvc().toString().length() != 3) {
      errors.put("cvc", "cvc 형식이 올바르지 않습니다. Payment.cvc: " + target.getCvc());
    }
  }
}