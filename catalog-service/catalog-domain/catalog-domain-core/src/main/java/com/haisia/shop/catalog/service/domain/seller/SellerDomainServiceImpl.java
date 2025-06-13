package com.haisia.shop.catalog.service.domain.seller;

import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.catalog.service.domain.seller.entity.SellerInitializer;
import com.haisia.shop.catalog.service.domain.seller.exception.SellerDomainException;

public class SellerDomainServiceImpl implements SellerDomainService {
  @Override
  public Object validateAndInitiate(Seller seller) {
    SellerInitializer sellerInitializer = new SellerInitializer(seller, SellerDomainException::new);
    sellerInitializer.validateAndInitialize();
    return null;
  }
}
