package com.haisia.shop.user.service.domain.entity;

import com.haisia.shop.common.domain.entity.BaseEntity;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.user.service.domain.valueobject.LedgerReason;
import com.haisia.shop.user.service.domain.valueobject.Mark;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ledger")
@Entity
public class Ledger extends BaseEntity<Long> {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @JoinColumn(name = "user_profile_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private UserProfile userProfile;

  private Mark mark;
  @AttributeOverride(name = "amount", column = @Column(name = "change", nullable = false))
  private Money change;
  @AttributeOverride(name = "amount", column = @Column(name = "before", nullable = false))
  private Money before;
  @AttributeOverride(name = "amount", column = @Column(name = "current", nullable = false))
  private Money current;
  private LedgerReason reason;
  private Instant processedAt;

  void initialize(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  @Builder
  private Ledger(Mark mark, Money change, Money before, LedgerReason reason, Instant processedAt) {
    if (mark == null || change == null || before == null || reason == null) {
      throw new DomainException("생성자 파라미터가 올바르지 않습니다.");
    }

    this.mark = mark;
    this.change = change;
    this.before = before;
    this.reason = reason;
    this.processedAt = processedAt == null ? Instant.now() : processedAt;

    this.current =
      switch (mark) {
        case INCREASE -> before.add(change);
        case DECREASE -> before.subtract(change);
      };

    if (current.isLessThanZero()) {
      throw new DomainException("잔액 계산 결과가 음수입니다: before=" + before + ", change=" + change);
    }
  }

  public static Ledger increase(Money before, Money change, LedgerReason reason) {
    return Ledger.builder()
      .mark(Mark.INCREASE)
      .change(change)
      .before(before)
      .reason(reason)
      .build();
  }

  public static Ledger decrease(Money before, Money change, LedgerReason reason) {
    return Ledger.builder()
      .mark(Mark.DECREASE)
      .change(change)
      .before(before)
      .reason(reason)
      .build();
  }
}