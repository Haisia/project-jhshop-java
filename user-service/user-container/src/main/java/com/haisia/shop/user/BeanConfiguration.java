package com.haisia.shop.user;

import com.haisia.shop.user.service.domain.UserProfileDomainService;
import com.haisia.shop.user.service.domain.UserProfileDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public UserProfileDomainService userProfileDomainService() {
    return new UserProfileDomainServiceImpl();
  }

}
