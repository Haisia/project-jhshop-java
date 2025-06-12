package com.haisia.shop.catalog.service.dataaccess.repository;

import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.common.domain.valueobject.id.SellerId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerJpaRepository extends JpaRepository<Seller, SellerId> {
  Optional<Seller> findByUserAuthId(UserAuthId userAuthId);
}
