package com.haisia.shop.catalog.service.domain.seller.entity;

import com.haisia.shop.catalog.service.domain.seller.exception.SellerDomainException;
import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.id.SellerId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "seller")
@Entity
public class Seller extends AggregateRoot<SellerId> {

  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private SellerId id;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "user_auth_id", columnDefinition = "uuid", nullable = false, unique = true)
  )
  @Embedded
  private UserAuthId userAuthId;

  @Builder
  private Seller(UserAuthId userAuthId) {
    this.userAuthId = userAuthId;
  }

  public void initialize() {
    setId(new SellerId(UUID.randomUUID()));
  }

  public void validate() {
    if (userAuthId == null || userAuthId.getValue() == null) {
      throw new SellerDomainException("Seller 의 userAuthId는 필수값입니다.");
    }
  }

  public void setId(SellerId id) {
    this.id = id;
  }
}
