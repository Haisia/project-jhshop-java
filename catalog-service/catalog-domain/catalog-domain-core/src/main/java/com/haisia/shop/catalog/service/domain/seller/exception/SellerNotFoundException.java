package com.haisia.shop.catalog.service.domain.seller.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class SellerNotFoundException extends DomainException {
  public SellerNotFoundException(String message) {
    super(message);
  }

  public SellerNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
