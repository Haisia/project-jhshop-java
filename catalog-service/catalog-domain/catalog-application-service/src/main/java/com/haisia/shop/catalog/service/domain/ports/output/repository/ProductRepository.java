package com.haisia.shop.catalog.service.domain.ports.output.repository;

import com.haisia.shop.catalog.service.domain.product.entity.Product;

public interface ProductRepository {
  Product save(Product product);
}
