package com.haisia.shop.user.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserProfileId;
import com.haisia.shop.user.service.domain.valueobject.LedgerReason;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_profile")
@Entity
public class UserProfile extends AggregateRoot<UserProfileId> {

  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private UserProfileId id;
  @AttributeOverride(
    name = "value",
    column = @Column(name = "user_auth_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private UserAuthId userAuthId;
  private String email;
  @Embedded
  private Address address;
  @Embedded
  private PhoneNumber phoneNumber;
  @Embedded
  private Money balance;

  @OrderBy("processedAt DESC")
  @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private final List<Ledger> ledgers = new ArrayList<>();

  // ---

  @Override
  protected void initialize() {
    this.id = new UserProfileId(UUID.randomUUID());
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
    this.id = userProfileId;
    this.userAuthId = userAuthId;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.balance = balance == null ? Money.ZERO : balance;
    if (ledgers != null) {
      this.ledgers.addAll(ledgers);
    }
  }

  // ---

  public void changeAddress(Address address) {
    this.address = address;
  }

  public void changePhoneNumber(PhoneNumber phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void increaseBalance(Money change, LedgerReason reason) {
    ledgers.add(Ledger.increase(this, balance, change, reason));
    this.balance = balance.add(change);
  }

  public void decreaseBalance(Money change, LedgerReason reason) {
    ledgers.add(Ledger.decrease(this, balance, change, reason));
    this.balance = balance.subtract(change);
  }
}
