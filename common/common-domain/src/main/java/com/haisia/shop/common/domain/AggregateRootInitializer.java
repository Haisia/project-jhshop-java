package com.haisia.shop.common.domain;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.id.BaseId;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class AggregateRootInitializer<T extends AggregateRoot<?>, E extends DomainException> {

  protected abstract void executeValidations(Map<String, String> errors);

  protected abstract void doInitialize();

  // ---

  protected final T target;
  private final Function<String, E> exceptionFactory;

  protected final String NULL = "null";

  public AggregateRootInitializer(T target, Function<String, E> exceptionFactory) {
    this.target = target;
    this.exceptionFactory = exceptionFactory;
  }

  public T getTarget() {
    return target;
  }

  public void validateAndInitialize() {
    validate();
    doInitialize();
  }

  public void validate() {
    Map<String, String> errors = new HashMap<>();
    executeValidations(errors);
    if (!errors.isEmpty()) {
      throw exceptionFactory.apply(generateErrorMessage(errors));
    }
  }

  protected String generateErrorMessage(Map<String, String> errors) {
    final String errorKeyValueDelimiter = ": ";
    final String errorKeyValueSeparator = ", ";

    return errors.entrySet().stream()
      .map(e -> e.getKey() + errorKeyValueDelimiter + e.getValue())
      .collect(Collectors.joining(errorKeyValueSeparator));
  }

  // ---

  protected void validateEmail(Map<String, String> errors, String email) {
    validateEmail(errors, email, "email");
  }

  protected void validateEmail(Map<String, String> errors, String email, String fieldName) {
    final String EMAIL_REGEX =
      "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    final int MIN_EMAIL_LENGTH = 5;
    final int MAX_EMAIL_LENGTH = 254;

    if (email == null || email.trim().isEmpty()) {
      errors.put(fieldName, NULL);
      return;
    }

    if (email.length() < MIN_EMAIL_LENGTH || email.length() > MAX_EMAIL_LENGTH) {
      errors.put(fieldName, String.format("email 은 %d 글자 이상, %d 글자 이하여야 합니다.", MIN_EMAIL_LENGTH, MAX_EMAIL_LENGTH));
    }

    Matcher matcher = EMAIL_PATTERN.matcher(email);
    if (!matcher.matches()) {
      errors.put(fieldName, "잘못된 email 형식입니다. - " + email);
    }
  }

  protected void validateBaseId(Map<String, String> errors, BaseId<?> baseId, String fieldName) {
    if (baseId == null || baseId.getValue() == null) {
      errors.put(fieldName, NULL);
      return;
    }
  }

  protected void validateAddress(Map<String, String> errors, Address address) {
    validateAddress(errors, address, "address");
  }

  protected void validateAddress(Map<String, String> errors, Address address, String fieldName) {
    if (address == null) {
      errors.put(fieldName, NULL);
      return;
    }

    if (address.country() == null || address.country().trim().isEmpty()) {
      errors.put(fieldName + ".country", NULL);
    }

    if (address.city() == null || address.city().trim().isEmpty()) {
      errors.put(fieldName + ".city", NULL);
    }

    if (address.street() == null || address.street().trim().isEmpty()) {
      errors.put(fieldName + ".street", NULL);
    }

    if (address.zipCode() == null || address.zipCode().trim().isEmpty()) {
      errors.put(fieldName + ".zipCode", NULL);
    }
  }
}
