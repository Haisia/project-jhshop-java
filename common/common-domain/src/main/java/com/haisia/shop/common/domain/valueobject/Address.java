package com.haisia.shop.common.domain.valueobject;

public record Address (
  String country,
  String city,
  String street,
  String zipCode,
  String detail
) {

}
