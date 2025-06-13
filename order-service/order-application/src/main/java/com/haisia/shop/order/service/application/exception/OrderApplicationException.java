package com.haisia.shop.order.service.application.exception;

import com.haisia.shop.common.application.exception.ApplicationException;

public class OrderApplicationException extends ApplicationException {
  public OrderApplicationException(String message) {
    super(message);
  }

  public OrderApplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
