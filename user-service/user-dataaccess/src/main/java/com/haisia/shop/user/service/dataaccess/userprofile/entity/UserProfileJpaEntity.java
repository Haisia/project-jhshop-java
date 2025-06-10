package com.haisia.shop.user.service.dataaccess.userprofile.entity;

import com.haisia.shop.common.dataaccess.jpa.entity.BaseJpaEntity;
import com.haisia.shop.common.dataaccess.jpa.valueobject.AddressJpaVo;
import jakarta.persistence.Embedded;
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
@Table(name = "user_profile")
@Entity
public class UserProfileJpaEntity extends BaseJpaEntity {

  @Id
  private UUID id;
  private UUID userAuthId;
  private String email;
  @Embedded
  private AddressJpaVo address;
  private String phoneNumber;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserProfileJpaEntity that = (UserProfileJpaEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
