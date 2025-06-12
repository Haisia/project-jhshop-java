package com.haisia.shop.catalog.service.domain.product.exception;

import com.haisia.shop.common.domain.exception.DomainException;

public class ProductDomainException extends DomainException {
  public ProductDomainException(String message) {
    super(message);
  }

  public ProductDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
