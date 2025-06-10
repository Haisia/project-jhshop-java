package com.haisia.shop.user.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserProfileId;
import lombok.Builder;

import java.util.UUID;

public class UserProfile extends AggregateRoot<UserProfileId> {
  private final UserAuthId userAuthId;
  private final String email;
  private final Address address;
  private final PhoneNumber phoneNumber;

  public void initialize() {
    setId(new UserProfileId(UUID.randomUUID()));
  }

  public UserAuthId getUserAuthId() {
    return userAuthId;
  }

  public String getEmail() {
    return email;
  }

  public Address getAddress() {
    return address;
  }

  public PhoneNumber getPhoneNumber() {
    return phoneNumber;
  }

  @Builder
  private UserProfile(
    UserProfileId userProfileId,
    UserAuthId userAuthId,
    String email,
    Address address,
    PhoneNumber phoneNumber
  ) {
    super.setId(userProfileId);
    this.userAuthId = userAuthId;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public void validate() {

  }
}
