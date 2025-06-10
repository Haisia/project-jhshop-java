package com.haisia.shop.auth.service.domain.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class RefreshTokenDomainException extends DomainException {
  public RefreshTokenDomainException(String message) {
    super(message);
  }

  public RefreshTokenDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
