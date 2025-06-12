package com.haisia.shop.auth.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserLoginRecordId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_login_record")
@Entity
public class UserLoginRecord extends AggregateRoot<UserLoginRecordId> {

  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private UserLoginRecordId id;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "user_auth_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private UserAuthId userAuthId;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private Instant succeedAt;
  @Column(nullable = false)
  private String ipAddress;
  @Column(nullable = false)
  private boolean isFirstLoginOfDay;

  @Builder
  private UserLoginRecord(
    UserLoginRecordId id,
    UserAuthId userAuthId,
    String email,
    Instant succeedAt,
    String ipAddress,
    boolean isFirstLoginOfDay
  ) {
    setId(id);
    this.userAuthId = userAuthId;
    this.email = email;
    this.succeedAt = succeedAt;
    this.ipAddress = ipAddress;
    this.isFirstLoginOfDay = isFirstLoginOfDay;
  }

  public UserAuthId getUserAuthId() {
    return userAuthId;
  }

  public String getEmail() {
    return email;
  }

  public Instant getSucceedAt() {
    return succeedAt;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public boolean isFirstLoginOfDay() {
    return isFirstLoginOfDay;
  }

  public void setFirstLoginOfDay(boolean firstLoginOfDay) {
    isFirstLoginOfDay = firstLoginOfDay;
  }

  @Override
  public void setId(UserLoginRecordId userLoginRecordId) {
    this.id = userLoginRecordId;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserLoginRecord that = (UserLoginRecord) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
