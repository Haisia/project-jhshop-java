package com.haisia.shop.auth.service.domain.refreshtokenrecord.entity;

import com.haisia.shop.auth.service.domain.refreshtokenrecord.exception.RefreshTokenRecordDomainException;
import com.haisia.shop.common.domain.AggregateRootInitializer;

import java.util.Map;
import java.util.function.Function;

public class RefreshTokenRecordInitializer
  extends AggregateRootInitializer<RefreshTokenRecord, RefreshTokenRecordDomainException> {

  public RefreshTokenRecordInitializer(RefreshTokenRecord target, Function<String, RefreshTokenRecordDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateToken(errors);
    validateUserAuthId(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
    target.setAvailable(true);
  }

  private void validateToken(Map<String, String> errors) {
    if (target.getToken() == null) {
      errors.put("token", NULL);
      return;
    }

    if (target.getToken().length() < 20) {
      errors.put("token", "토큰은 20글자 이상이어야 합니다.");
    }

    if (!target.getToken().contains(".") || target.getToken().chars().filter(ch -> ch == '.').count() < 2) {
      errors.put("token", "토큰의 형식이 올바르지 않습니다. - " + target.getToken());
    }
  }

  private void validateUserAuthId(Map<String, String> errors) {
    validateBaseId(errors, target.getUserAuthId(), "userAuthId");
  }
}
