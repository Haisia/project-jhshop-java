package com.haisia.shop.auth.service.dataaccess.userloginrecord.entity;

import com.haisia.shop.common.dataaccess.jpa.entity.BaseJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_login_record")
@Entity
public class UserLoginRecordJpaEntity extends BaseJpaEntity {

  @Id
  private UUID id;
  private UUID userAuthId;
  private String email;
  private Instant succeedAt;
  private String ipAddress;
  private boolean isFirstLoginOfDay;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserLoginRecordJpaEntity that = (UserLoginRecordJpaEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
