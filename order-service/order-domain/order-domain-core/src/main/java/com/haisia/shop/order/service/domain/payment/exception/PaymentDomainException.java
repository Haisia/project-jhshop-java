package com.haisia.shop.order.service.domain.payment.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {
  public PaymentDomainException(String message) {
    super(message);
  }

  public PaymentDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
