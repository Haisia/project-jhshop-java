package com.haisia.shop;

import com.haisia.shop.auth.service.domain.RefreshTokenRecordDomainService;
import com.haisia.shop.auth.service.domain.RefreshTokenRecordDomainServiceImpl;
import com.haisia.shop.auth.service.domain.UserAuthDomainService;
import com.haisia.shop.auth.service.domain.UserAuthDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public UserAuthDomainService userAuthDomainService() {
    return new UserAuthDomainServiceImpl();
  }

  @Bean
  public RefreshTokenRecordDomainService refreshTokenDomainService() {
    return new RefreshTokenRecordDomainServiceImpl();
  }

}
