package com.haisia.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(
  basePackages = {
    "com.haisia.shop.user.service.dataaccess",
    "com.haisia.shop.common.dataaccess"
  }
)
@EntityScan(
  basePackages = {
    "com.haisia.shop.user.service.dataaccess",
    "com.haisia.shop.common.dataaccess"
  }
)
@SpringBootApplication(scanBasePackages = "com.haisia.shop")
public class UserServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }
}
