package com.haisia.shop.catalog.service.dataaccess.adapter;

import com.haisia.shop.catalog.service.dataaccess.repository.SellerJpaRepository;
import com.haisia.shop.catalog.service.domain.ports.output.repository.SellerRepository;
import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.common.domain.valueobject.id.SellerId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SellerRepositoryImpl implements SellerRepository {

  private final SellerJpaRepository repository;

  @Override
  public Seller save(Seller seller) {
    return repository.save(seller);
  }

  @Override
  public Optional<Seller> findByUserAuthId(UserAuthId userAuthId) {
    return repository.findByUserAuthId(userAuthId);
  }

  @Override
  public Optional<Seller> findById(SellerId id) {
    return repository.findById(id);
  }
}
