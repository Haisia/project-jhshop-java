package com.haisia.shop.catalog.service.domain.ports.output.repository;

import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.common.domain.valueobject.id.ProductId;

import java.util.Optional;

public interface ProductRepository {
  Product save(Product product);
  Optional<Product> findById(ProductId id);
}
