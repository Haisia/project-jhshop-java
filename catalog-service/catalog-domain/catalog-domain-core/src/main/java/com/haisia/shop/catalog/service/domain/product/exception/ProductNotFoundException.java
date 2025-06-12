package com.haisia.shop.catalog.service.domain.product.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class ProductNotFoundException extends DomainException {
  public ProductNotFoundException(String message) {
    super(message);
  }

  public ProductNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
