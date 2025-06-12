package com.haisia.shop.auth.service.application.exception;

public class AuthApplicationException extends RuntimeException {
  public AuthApplicationException(String message) {
    super(message);
  }

  public AuthApplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
