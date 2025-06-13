package com.haisia.shop.catalog.service.domain.product;

import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.catalog.service.domain.product.exception.ProductDomainException;
import com.haisia.shop.catalog.service.domain.product.entity.ProductInitializer;
import com.haisia.shop.common.domain.event.DomainEvent;

public class ProductDomainServiceImpl implements ProductDomainService {

  @Override
  public DomainEvent validateAndInitiate(Product product) {
    ProductInitializer productInitializer = new ProductInitializer(product, ProductDomainException::new);
    productInitializer.validateAndInitialize();
    return null;
  }
}
