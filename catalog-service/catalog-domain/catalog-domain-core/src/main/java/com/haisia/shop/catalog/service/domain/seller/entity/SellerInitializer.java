package com.haisia.shop.catalog.service.domain.seller.entity;

import com.haisia.shop.catalog.service.domain.seller.exception.SellerDomainException;
import com.haisia.shop.common.domain.AggregateRootInitializer;

import java.util.Map;
import java.util.function.Function;

public class SellerInitializer extends AggregateRootInitializer<Seller, SellerDomainException> {

  public SellerInitializer(Seller target, Function<String, SellerDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateUserAuthId(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
  }

  private void validateUserAuthId(Map<String, String> errors) {
    validateBaseId(errors, target.getUserAuthId(), "userAuthId");
  }

}
