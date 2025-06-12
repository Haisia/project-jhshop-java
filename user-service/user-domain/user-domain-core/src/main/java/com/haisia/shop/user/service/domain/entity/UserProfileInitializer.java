package com.haisia.shop.user.service.domain.entity;

import com.haisia.shop.common.domain.AggregateRootInitializer;
import com.haisia.shop.user.service.domain.exception.UserProfileDomainException;

import java.util.Map;
import java.util.function.Function;

public class UserProfileInitializer extends AggregateRootInitializer<UserProfile, UserProfileDomainException> {

  public UserProfileInitializer(UserProfile target, Function<String, UserProfileDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateUserAuthId(errors);
    validateEmail(errors);
    validateAddress(errors);
    validatePhoneNumber(errors);
    validateBalance(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
  }

  private void validateUserAuthId(Map<String, String> errors) {
    validateBaseId(errors, target.getUserAuthId(), "userAuthId");
  }

  private void validateEmail(Map<String, String> errors) {
    super.validateEmail(errors, target.getEmail());
  }

  private void validateAddress(Map<String, String> errors) {
    super.validateAddress(errors, target.getAddress());
  }

  private void validatePhoneNumber(Map<String, String> errors) {
    if (target.getPhoneNumber() == null || target.getPhoneNumber().number() == null) {
      errors.put("phoneNumber", NULL);
      return;
    }
  }

  private void validateBalance(Map<String, String> errors) {
    if (target.getBalance() == null || target.getBalance().amount() == null) {
      errors.put("balance", NULL);
      return;
    }
  }
}
