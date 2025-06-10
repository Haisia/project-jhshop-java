package com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.entity;

import com.haisia.shop.common.dataaccess.jpa.entity.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token_record")
@Entity
public class RefreshTokenRecordJpaEntity extends BaseJpaEntity {
  @Id
  private UUID id;

  @Column(unique = true, nullable = false, length = 1024)
  private String token;
  @Column(nullable = false)
  private UUID userAuthId;
  private int refreshCount;
  private boolean available;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    RefreshTokenRecordJpaEntity that = (RefreshTokenRecordJpaEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
