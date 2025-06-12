package com.haisia.shop.catalog.service.dataaccess.product.repository;

import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, ProductId> {
}
