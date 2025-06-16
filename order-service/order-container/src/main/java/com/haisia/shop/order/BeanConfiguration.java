package com.haisia.shop.order;

import com.haisia.shop.order.service.domain.order.OrderDomainService;
import com.haisia.shop.order.service.domain.order.OrderDomainServiceImpl;
import com.haisia.shop.order.service.domain.payment.PaymentDomainService;
import com.haisia.shop.order.service.domain.payment.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public OrderDomainService orderDomainService() {
    return new OrderDomainServiceImpl();
  }

  @Bean
  public PaymentDomainService paymentDomainService() {
    return new PaymentDomainServiceImpl();
  }

}
