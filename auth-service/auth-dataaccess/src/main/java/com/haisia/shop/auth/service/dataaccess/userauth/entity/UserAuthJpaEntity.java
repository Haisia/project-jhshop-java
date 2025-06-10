package com.haisia.shop.auth.service.dataaccess.userauth.entity;

import com.haisia.shop.common.dataaccess.jpa.entity.BaseJpaEntity;
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
@Table(name = "user_auth")
@Entity
public class UserAuthJpaEntity extends BaseJpaEntity {

  @Id
  private UUID id;

  private String email;
  private String hashedPassword;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserAuthJpaEntity that = (UserAuthJpaEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
