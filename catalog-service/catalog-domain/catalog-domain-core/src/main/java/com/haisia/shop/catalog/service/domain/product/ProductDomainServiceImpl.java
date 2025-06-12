package com.haisia.shop.catalog.service.domain.product;

import com.haisia.shop.catalog.service.domain.product.entity.Product;

public class ProductDomainServiceImpl implements ProductDomainService {

  @Override
  public void validateAndInitiate(Product product) {
    product.validate();
    product.initialize();
  }
}
