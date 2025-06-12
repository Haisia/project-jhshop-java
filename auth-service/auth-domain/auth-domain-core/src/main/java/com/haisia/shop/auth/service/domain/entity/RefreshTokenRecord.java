package com.haisia.shop.auth.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.id.RefreshTokenRecordId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "refresh_token_record")
@Entity
public class RefreshTokenRecord extends AggregateRoot<RefreshTokenRecordId> {

  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private RefreshTokenRecordId id;
  @Column(unique = true, nullable = false, length = 1024)
  private String token;
  @AttributeOverride(
    name = "value",
    column = @Column(name = "user_auth_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private UserAuthId userAuthId;
  @Column(nullable = false)
  private int refreshCount;
  @Column(nullable = false)
  private boolean available;

  public void initialize() {
    setId(new RefreshTokenRecordId(UUID.randomUUID()));
  }

  public String getToken() {
    return token;
  }

  public UserAuthId getUserAuthId() {
    return userAuthId;
  }

  public int getRefreshCount() {
    return refreshCount;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public void addRefreshCount() {
    refreshCount++;
  }

  @Builder
  private RefreshTokenRecord(RefreshTokenRecordId id, String token, UserAuthId userAuthId, int refreshCount, boolean available) {
    this.id = id;
    this.token = token;
    this.userAuthId = userAuthId;
    this.refreshCount = refreshCount;
    this.available = available;
  }

  public void setId(RefreshTokenRecordId refreshTokenRecordId) {
    this.id = refreshTokenRecordId;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    RefreshTokenRecord that = (RefreshTokenRecord) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
