package com.haisia.shop.catalog.service.domain.product;

import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.catalog.service.domain.product.exception.ProductDomainException;
import com.haisia.shop.catalog.service.domain.product.entity.ProductInitializer;

public class ProductDomainServiceImpl implements ProductDomainService {

  @Override
  public void validateAndInitiate(Product product) {
    ProductInitializer productInitializer = new ProductInitializer(product, ProductDomainException::new);
    productInitializer.validateAndInitialize();
  }
}
