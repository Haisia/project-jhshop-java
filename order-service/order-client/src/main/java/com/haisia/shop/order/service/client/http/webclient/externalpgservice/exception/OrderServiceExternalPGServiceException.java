package com.haisia.shop.order.service.client.http.webclient.externalpgservice.exception;

public class OrderServiceExternalPGServiceException extends RuntimeException {
  public OrderServiceExternalPGServiceException(String message) {
    super(message);
  }

  public OrderServiceExternalPGServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
