package com.haisia.shop.auth.service.domain.userauth.entity;

import com.haisia.shop.auth.service.domain.userauth.exception.UserAuthDomainException;
import com.haisia.shop.common.domain.AggregateRootInitializer;

import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAuthInitializer extends AggregateRootInitializer<UserAuth, UserAuthDomainException> {

  public UserAuthInitializer(UserAuth target, Function<String, UserAuthDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateEmail(errors, target.getEmail());
    validateHashedPassword(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
  }

  private void validateHashedPassword(Map<String, String> errors) {
    if (target.getHashedPassword() == null || target.getHashedPassword().trim().isEmpty()) {
      errors.put("hashedPassword", NULL);
      return;
    }
  }
}
