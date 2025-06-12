package com.haisia.shop;

import com.haisia.shop.catalog.service.domain.product.ProductDomainService;
import com.haisia.shop.catalog.service.domain.product.ProductDomainServiceImpl;
import com.haisia.shop.catalog.service.domain.seller.SellerDomainService;
import com.haisia.shop.catalog.service.domain.seller.SellerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public SellerDomainService sellerDomainService() {
    return new SellerDomainServiceImpl();
  }

  @Bean
  public ProductDomainService productDomainService() {
    return new ProductDomainServiceImpl();
  }

}
