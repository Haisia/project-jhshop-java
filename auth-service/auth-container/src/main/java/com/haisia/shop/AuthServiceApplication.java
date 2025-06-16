package com.haisia.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.haisia.shop.auth", "com.haisia.shop.common"})
@EnableFeignClients(basePackages = {"com.haisia.shop.auth", "com.haisia.shop.common"})
@EntityScan(basePackages = {"com.haisia.shop.auth", "com.haisia.shop.common"})
@SpringBootApplication(scanBasePackages = {"com.haisia.shop.auth", "com.haisia.shop.common"})
public class AuthServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthServiceApplication.class, args);
  }
}
