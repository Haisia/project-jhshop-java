package com.haisia.shop.auth.service.client.http.feign.userservice.exception;

public class UserServiceFeignException extends RuntimeException {
  public UserServiceFeignException(String message) {
    super(message);
  }

  public UserServiceFeignException(String message, Throwable cause) {
    super(message, cause);
  }
}
