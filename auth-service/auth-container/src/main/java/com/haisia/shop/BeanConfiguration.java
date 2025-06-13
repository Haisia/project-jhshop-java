package com.haisia.shop;

import com.haisia.shop.auth.service.domain.refreshtokenrecord.RefreshTokenRecordDomainService;
import com.haisia.shop.auth.service.domain.refreshtokenrecord.RefreshTokenRecordDomainServiceImpl;
import com.haisia.shop.auth.service.domain.userauth.UserAuthDomainService;
import com.haisia.shop.auth.service.domain.userauth.UserAuthDomainServiceImpl;
import com.haisia.shop.auth.service.domain.userloginrecord.UserLoginRecordDomainService;
import com.haisia.shop.auth.service.domain.userloginrecord.UserLoginRecordDomainServiceImpl;
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

  @Bean
  public UserLoginRecordDomainService userLoginRecordDomainService() {
    return new UserLoginRecordDomainServiceImpl();
  }

}
