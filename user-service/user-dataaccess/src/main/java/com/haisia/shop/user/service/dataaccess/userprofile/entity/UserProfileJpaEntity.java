package com.haisia.shop.user.service.dataaccess.userprofile.entity;

import com.haisia.shop.common.domain.entity.BaseJpaEntity;
import com.haisia.shop.common.dataaccess.jpa.valueobject.AddressJpaVo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
  @Column(unique = true, nullable = false)
  private UUID userAuthId;
  @Column(unique = true, nullable = false)
  private String email;
  @Embedded
  private AddressJpaVo address;
  private String phoneNumber;
  private BigDecimal balance;

  @OrderBy("processedAt DESC")
  @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<LedgerJpaEntity> ledgers = new ArrayList<>();

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
