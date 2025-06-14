package com.haisia.shop.common.domain.valueobject;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public record Stock(
  BigDecimal amount
) {
  public static final Stock ZERO = new Stock(BigDecimal.ZERO);

  public Stock {
    if (amount != null) amount = amount.setScale(0, RoundingMode.DOWN);
  }

  public Stock(Integer amount) {
    this(new BigDecimal(amount));
  }

  public boolean isGreaterThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreaterThan(Stock money) {
    return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
  }

  public boolean isLessThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) < 0;
  }

  public boolean isLessThan(Stock money) {
    return this.amount != null && this.amount.compareTo(money.getAmount()) < 0;
  }

  public boolean isNotNegative() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) >= 0;
  }

  public Stock add(Stock money) {
    return new Stock(setScale(this.amount.add(money.getAmount())));
  }

  public Stock subtract(Stock money) {
    return new Stock(setScale(this.amount.subtract(money.getAmount())));
  }

  public Stock multiply(int multiplier) {
    return new Stock(setScale(this.amount.multiply(new BigDecimal(multiplier))));
  }

  public BigDecimal getAmount() {
    return amount;
  }

  private BigDecimal setScale(BigDecimal input) {
    return input.setScale(2, RoundingMode.HALF_EVEN);
  }
}
