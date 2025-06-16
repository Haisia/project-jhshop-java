package com.haisia.shop.order;

import com.haisia.shop.order.service.domain.order.OrderDomainService;
import com.haisia.shop.order.service.domain.order.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public OrderDomainService orderDomainService() {
    return new OrderDomainServiceImpl();
  }

}
