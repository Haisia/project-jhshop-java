package com.haisia.shop.auth.service.domain.event;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.common.domain.event.payload.UserAuthCreatedEventPayload;
import com.haisia.shop.common.domain.event.publisher.DomainEventPublisher;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;

import java.time.ZonedDateTime;
import java.util.UUID;

public class UserAuthCreatedEvent extends UserAuthEvent {
  private final Address address;
  private final PhoneNumber phoneNumber;

  public UserAuthCreatedEventPayload payload() {
    return new UserAuthCreatedEventPayload(
      UUID.randomUUID().toString(),
      getCreatedAt(),
      getUserAuth().getId().getValue().toString(),
      getUserAuth().getEmail(),
      address,
      phoneNumber
    );
  }

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
