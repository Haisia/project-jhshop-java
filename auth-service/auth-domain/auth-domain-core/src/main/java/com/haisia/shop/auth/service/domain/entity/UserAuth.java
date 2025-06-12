package com.haisia.shop.auth.service.domain.entity;

import com.haisia.shop.auth.service.domain.exception.UserAuthDomainException;
import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_auth")
@Entity
public class UserAuth extends AggregateRoot<UserAuthId> {

  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private UserAuthId id;
  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
  private String hashedPassword;

  public void initialize() {
    setId(new UserAuthId(UUID.randomUUID()));
  }

  public String getEmail() {
    return email;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  @Builder
  private UserAuth(UserAuthId userAuthId, String email, String hashedPassword) {
    this.id = userAuthId;
    this.email = email;
    this.hashedPassword = hashedPassword;
  }

  public void validate() {
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

  @Override
  public void setId(UserAuthId userAuthId) {
    this.id = userAuthId;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserAuth userAuth = (UserAuth) o;
    return Objects.equals(id, userAuth.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
