package com.haisia.shop.auth.service.domain.refreshtokenrecord.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class RefreshTokenRecordDomainException extends DomainException {
  public RefreshTokenRecordDomainException(String message) {
    super(message);
  }

  public RefreshTokenRecordDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
