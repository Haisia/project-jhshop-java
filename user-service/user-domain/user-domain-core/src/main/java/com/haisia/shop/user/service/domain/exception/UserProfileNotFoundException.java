package com.haisia.shop.user.service.domain.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class UserProfileNotFoundException extends DomainException {
  public UserProfileNotFoundException(String message) {
    super(message);
  }

  public UserProfileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
