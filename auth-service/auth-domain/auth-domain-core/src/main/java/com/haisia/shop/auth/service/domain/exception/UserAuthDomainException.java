package com.haisia.shop.auth.service.domain.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class UserAuthDomainException extends DomainException {
  public UserAuthDomainException(String message) {
    super(message);
  }

  public UserAuthDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
