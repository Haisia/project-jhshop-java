package com.haisia.shop.catalog.service.dataaccess.product.adapter;

import com.haisia.shop.catalog.service.dataaccess.product.repository.ProductJpaRepository;
import com.haisia.shop.catalog.service.domain.ports.output.repository.ProductRepository;
import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductJpaRepository repository;

  @Override
  public Product save(Product product) {
    return repository.save(product);
  }

  @Override
  public Optional<Product> findById(ProductId id) {
    return repository.findById(id);
  }
}
