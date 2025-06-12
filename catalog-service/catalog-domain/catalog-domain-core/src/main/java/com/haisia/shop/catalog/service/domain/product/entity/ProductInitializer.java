package com.haisia.shop.catalog.service.domain.product.entity;

import com.haisia.shop.catalog.service.domain.product.exception.ProductDomainException;
import com.haisia.shop.common.domain.AggregateRootInitializer;

import java.util.Map;
import java.util.function.Function;

public class ProductInitializer extends AggregateRootInitializer<Product, ProductDomainException> {

  public ProductInitializer(Product target, Function<String, ProductDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateName(errors);
    validatePrice(errors);
    validateStock(errors);
    validateInformation(errors);
    validateSellerId(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
  }

  private void validateName(Map<String, String> errors) {
    if (target.getName() == null || target.getName().length() < 2) {
      errors.put("name", target.getName());
    }
  }

  private void validatePrice(Map<String, String> errors) {
    if (target.getPrice() == null || !target.getPrice().isGreaterThanZero()) {
      errors.put("price", target.getPrice() == null ? NULL : target.getPrice().toString());
    }
  }

  private void validateStock(Map<String, String> errors) {
    if (target.getStock() == null || !target.getStock().isGreaterThanZero()) {
      errors.put("stock", target.getStock() == null ? NULL : target.getStock().toString());
    }
  }

  private void validateInformation(Map<String, String> errors) {
    if (target.getInformation() == null) {
      errors.put("information", NULL);
      return;
    }

    if (target.getInformation().title() == null || target.getInformation().title().length() < 2) {
      errors.put("information.title", target.getInformation().title() == null ? NULL : target.getInformation().title());
    }

    if (target.getInformation().content() == null || target.getInformation().content().length() < 2) {
      errors.put("information.content", target.getInformation().content() == null ? NULL : target.getInformation().content());
    }
  }

  private void validateSellerId(Map<String, String> errors) {
    validateBaseId(errors, target.getSellerId(), "sellerId");
  }
}
