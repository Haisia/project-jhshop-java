package com.haisia.shop.catalog.service.domain.seller;

import com.haisia.shop.catalog.service.domain.seller.entity.Seller;

public interface SellerDomainService {
  void validateAndInitiate(Seller seller);
}
