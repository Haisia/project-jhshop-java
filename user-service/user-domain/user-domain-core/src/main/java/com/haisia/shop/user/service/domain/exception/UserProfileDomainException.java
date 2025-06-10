package com.haisia.shop.user.service.domain.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class UserProfileDomainException extends DomainException {
  public UserProfileDomainException(String message) {
    super(message);
  }

  public UserProfileDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
