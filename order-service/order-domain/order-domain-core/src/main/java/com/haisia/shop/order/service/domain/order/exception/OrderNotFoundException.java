package com.haisia.shop.order.service.domain.order.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class OrderNotFoundException extends OrderDomainException {
  public OrderNotFoundException(String message) {
    super(message);
  }

  public OrderNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
