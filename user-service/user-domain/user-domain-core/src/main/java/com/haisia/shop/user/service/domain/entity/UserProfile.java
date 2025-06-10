package com.haisia.shop.user.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserProfileId;
import lombok.Builder;

import java.util.UUID;

public class UserProfile extends AggregateRoot<UserProfileId> {
  private final UserAuthId userAuthId;
  private final String email;
  private Address address;
  private PhoneNumber phoneNumber;
  private Money balance;

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

  public Money getBalance() {
    return balance;
  }

  public void changeAddress(Address address) {
    this.address = address;
  }

  public void changePhoneNumber(PhoneNumber phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void increaseBalance(Money money) {
    this.balance = balance.add(money);
  }

  public void decreaseBalance(Money money) {
    this.balance = balance.subtract(money);
  }

  @Builder
  private UserProfile(
    UserProfileId userProfileId,
    UserAuthId userAuthId,
    String email,
    Address address,
    PhoneNumber phoneNumber,
    Money balance
  ) {
    super.setId(userProfileId);
    this.userAuthId = userAuthId;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.balance = balance == null ? Money.ZERO : balance;
  }

  public void validate() {
  }
}
