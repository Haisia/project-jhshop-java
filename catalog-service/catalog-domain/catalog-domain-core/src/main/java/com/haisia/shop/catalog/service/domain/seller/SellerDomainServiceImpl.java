package com.haisia.shop.catalog.service.domain.seller;

import com.haisia.shop.catalog.service.domain.seller.entity.Seller;

public class SellerDomainServiceImpl implements SellerDomainService {
  @Override
  public void validateAndInitiate(Seller seller) {
    seller.validate();
    seller.initialize();
  }
}
