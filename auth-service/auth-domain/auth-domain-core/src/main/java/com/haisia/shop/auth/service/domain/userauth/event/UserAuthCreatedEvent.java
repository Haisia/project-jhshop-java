package com.haisia.shop.auth.service.domain.userauth.event;

import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;

import java.time.ZonedDateTime;

public class UserAuthCreatedEvent extends UserAuthEvent {
  private final Address address;
  private final PhoneNumber phoneNumber;

  public UserAuthCreatedEvent(
    UserAuth userAuth,
    ZonedDateTime createdAt,
    Address address,
    PhoneNumber phoneNumber
  ) {
    super(userAuth, createdAt);
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public Address getAddress() {
    return address;
  }

  public PhoneNumber getPhoneNumber() {
    return phoneNumber;
  }
}
