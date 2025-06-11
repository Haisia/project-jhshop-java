package com.haisia.shop.user.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserProfileId;
import com.haisia.shop.user.service.domain.valueobject.LedgerReason;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserProfile extends AggregateRoot<UserProfileId> {
  private final UserAuthId userAuthId;
  private final String email;
  private Address address;
  private PhoneNumber phoneNumber;
  private Money balance;

  private final List<Ledger> ledgers = new ArrayList<>();

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

  public List<Ledger> getLedgers() {
    return ledgers;
  }

  public void changeAddress(Address address) {
    this.address = address;
  }

  public void changePhoneNumber(PhoneNumber phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void increaseBalance(Money change, LedgerReason reason) {
    ledgers.add(Ledger.increase(balance, change, reason));
    this.balance = balance.add(change);
  }

  public void decreaseBalance(Money change, LedgerReason reason) {
    ledgers.add(Ledger.decrease(balance, change, reason));
    this.balance = balance.subtract(change);
  }

  @Builder
  private UserProfile(
    UserProfileId userProfileId,
    UserAuthId userAuthId,
    String email,
    Address address,
    PhoneNumber phoneNumber,
    Money balance,
    List<Ledger> ledgers
  ) {
    super.setId(userProfileId);
    this.userAuthId = userAuthId;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.balance = balance == null ? Money.ZERO : balance;
    if (ledgers != null) {
      this.ledgers.addAll(ledgers);
    }
  }

  public void validate() {
  }
}
