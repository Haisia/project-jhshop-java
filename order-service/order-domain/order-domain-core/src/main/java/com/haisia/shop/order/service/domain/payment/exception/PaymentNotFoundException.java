package com.haisia.shop.order.service.domain.payment.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class PaymentNotFoundException extends DomainException {
  public PaymentNotFoundException(String message) {
    super(message);
  }

  public PaymentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
