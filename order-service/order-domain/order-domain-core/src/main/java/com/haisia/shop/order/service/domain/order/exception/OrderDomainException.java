package com.haisia.shop.order.service.domain.order.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
  public OrderDomainException(String message) {
    super(message);
  }

  public OrderDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
