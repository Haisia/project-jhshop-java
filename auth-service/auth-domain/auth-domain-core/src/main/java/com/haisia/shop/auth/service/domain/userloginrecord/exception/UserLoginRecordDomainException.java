package com.haisia.shop.auth.service.domain.userloginrecord.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class UserLoginRecordDomainException extends DomainException {
  public UserLoginRecordDomainException(String message) {
    super(message);
  }

  public UserLoginRecordDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
