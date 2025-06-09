package com.haisia.shop.common.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseData<T> {
  private T data;
  private int status;
  private String errorCode;
  private String errorMessage;

  private static final String EMPTY_STRING = "";

  public static <T> ResponseData<T> success(T data) {
    return new ResponseData<>(data, 200, EMPTY_STRING, EMPTY_STRING);
  }

  public static <T> ResponseData<T> failure(int status, String errorCode, String errorMessage) {
    return new ResponseData<>(null, status, errorCode, errorMessage);
  }
}
