package com.haisia.shop.catalog.service.domain.seller;

import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.catalog.service.domain.seller.entity.SellerInitializer;
import com.haisia.shop.catalog.service.domain.seller.exception.SellerDomainException;
import com.haisia.shop.common.domain.event.DomainEvent;

public class SellerDomainServiceImpl implements SellerDomainService {
  @Override
  public DomainEvent validateAndInitiate(Seller seller) {
    SellerInitializer sellerInitializer = new SellerInitializer(seller, SellerDomainException::new);
    sellerInitializer.validateAndInitialize();
    return null;
  }
}
