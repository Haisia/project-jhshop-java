package com.haisia.shop.auth.service.domain.userloginrecord.entity;

import com.haisia.shop.auth.service.domain.userloginrecord.exception.UserLoginRecordDomainException;
import com.haisia.shop.common.domain.AggregateRootInitializer;

import java.util.Map;
import java.util.function.Function;

public class UserLoginRecordInitializer extends AggregateRootInitializer<UserLoginRecord, UserLoginRecordDomainException> {

  public UserLoginRecordInitializer(UserLoginRecord target, Function<String, UserLoginRecordDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateUserAuthId(errors);
    validateEmail(errors, target.getEmail());
    validateSucceedAt(errors);
    validateIpAddress(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
  }

  private void validateUserAuthId(Map<String, String> errors) {
    validateBaseId(errors, target.getUserAuthId(), "userAuthId");
  }

  private void validateSucceedAt(Map<String, String> errors) {
    if (target.getSucceedAt() == null) {
      errors.put("succeedAt", NULL);
      return;
    }
  }

  private void validateIpAddress(Map<String, String> errors) {
    if (target.getIpAddress() == null || target.getIpAddress().trim().isEmpty()) {
      errors.put("ipAddress", NULL);
    }
  }

}
