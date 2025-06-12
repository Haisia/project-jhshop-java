package com.haisia.shop.common.application.config;

import com.haisia.shop.common.application.UserSessionFactory;
import com.haisia.shop.common.application.annotation.InternalOnlyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CommonWebMvcCustomizer {

  @Bean
  public WebMvcConfigurer commonConfigurer(
    InternalOnlyInterceptor interceptor,
    UserSessionFactory userSessionFactory
  ) {
    return new WebMvcConfigurer() {
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
      }

      @Override
      public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserSessionArgumentResolver(userSessionFactory));
      }
    };
  }
}