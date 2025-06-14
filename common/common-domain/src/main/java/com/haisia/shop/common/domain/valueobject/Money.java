package com.haisia.shop.common.domain.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public record Money(
  BigDecimal amount
) {
  public static final Money ZERO = new Money(BigDecimal.ZERO);

  public Money {
    if (amount != null) amount = amount.setScale(0, RoundingMode.DOWN);
  }

  public Money(Integer amount) {
    this(new BigDecimal(amount).setScale(0, RoundingMode.DOWN));
  }

  public boolean isGreaterThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreaterThan(Money money) {
    return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
  }

  public boolean isLessThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) < 0;
  }

  public boolean isLessThan(Money money) {
    return this.amount != null && this.amount.compareTo(money.getAmount()) < 0;
  }

  public Money add(Money money) {
    return new Money(this.amount.add(money.getAmount()));
  }

  public Money subtract(Money money) {
    return new Money(this.amount.subtract(money.getAmount()));
  }

  public Money multiply(int multiplier) {
    return new Money(this.amount.multiply(new BigDecimal(multiplier)));
  }

  public BigDecimal getAmount() {
    return amount;
  }

}
