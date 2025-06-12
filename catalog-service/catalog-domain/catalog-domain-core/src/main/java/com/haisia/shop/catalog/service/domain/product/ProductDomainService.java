package com.haisia.shop.catalog.service.domain.product;

import com.haisia.shop.catalog.service.domain.product.entity.Product;

public interface ProductDomainService {
  void validateAndInitiate(Product product);
}
