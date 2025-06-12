package com.haisia.shop.catalog.service.domain.ports.output.repository;

import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.common.domain.valueobject.id.SellerId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;

import java.util.Optional;

public interface SellerRepository {
  Seller save(Seller seller);
  Optional<Seller> findByUserAuthId(UserAuthId userAuthId);
  Optional<Seller> findById(SellerId id);
}
