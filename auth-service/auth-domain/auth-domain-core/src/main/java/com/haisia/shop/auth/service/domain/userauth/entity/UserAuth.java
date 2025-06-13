package com.haisia.shop.auth.service.domain.userauth.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
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

  // ---

  @Override
  protected void initialize() {
    this.id = new UserAuthId(UUID.randomUUID());
  }

  public String getEmail() {
    return email;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  @Builder
  private UserAuth(String email, String hashedPassword) {
    this.email = email;
    this.hashedPassword = hashedPassword;
  }

  // ---

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

  public void changePassword(String newHashedPassword) {
    this.hashedPassword = newHashedPassword;
  }
}
