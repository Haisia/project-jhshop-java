package com.haisia.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.haisia.shop.order", "com.haisia.shop.common"})
@EntityScan(basePackages = {"com.haisia.shop.order", "com.haisia.shop.common"})
@SpringBootApplication(scanBasePackages = {"com.haisia.shop.order", "com.haisia.shop.common"})
public class OrderServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }
}
