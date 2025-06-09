package com.haisia.shop.auth.service.domain.entity;

import com.haisia.shop.auth.service.domain.exception.UserAuthDomainException;
import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.UserId;
import lombok.Builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAuth extends AggregateRoot<UserId> {
  private final String email;
  private final String hashedPassword;

  @Builder
  private UserAuth(UserId userId, String email, String hashedPassword) {
    super.setId(userId);
    this.email = email;
    this.hashedPassword = hashedPassword;
  }

  public void validateUserAuth() {
    validateEmail();
  }

  private void validateEmail() {
    final String EMAIL_REGEX =
      "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    final int MIN_EMAIL_LENGTH = 5;
    final int MAX_EMAIL_LENGTH = 254;

    if (email == null || email.trim().isEmpty()) {
      throw new UserAuthDomainException("email 은 null 이거나 비어있을 수 없습니다.");
    }

    if (email.length() < MIN_EMAIL_LENGTH || email.length() > MAX_EMAIL_LENGTH) {
      throw new UserAuthDomainException(
        String.format("email 은 %d 이상, %d 이하여야 합니다.",
          MIN_EMAIL_LENGTH, MAX_EMAIL_LENGTH));
    }

    Matcher matcher = EMAIL_PATTERN.matcher(email);
    if (!matcher.matches()) {
      throw new UserAuthDomainException("잘못된 email 형식입니다.");
    }
  }

  public String getEmail() {
    return email;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }
}
