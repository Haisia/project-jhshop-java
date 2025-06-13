package com.haisia.shop;

import com.haisia.shop.order.service.domain.OrderDomainService;
import com.haisia.shop.order.service.domain.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public OrderDomainService orderDomainService() {
    return new OrderDomainServiceImpl();
  }

}
