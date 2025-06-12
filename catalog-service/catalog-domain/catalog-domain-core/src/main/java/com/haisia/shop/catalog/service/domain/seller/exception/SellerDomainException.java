package com.haisia.shop.catalog.service.domain.seller.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class SellerDomainException extends DomainException {
  public SellerDomainException(String message) {
    super(message);
  }

  public SellerDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
