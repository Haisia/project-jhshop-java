package com.haisia.shop.user.service.domain;

import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.entity.UserProfileInitializer;
import com.haisia.shop.user.service.domain.event.UserProfileCreatedEvent;
import com.haisia.shop.user.service.domain.exception.UserProfileDomainException;
import com.haisia.shop.user.service.domain.valueobject.LedgerReason;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.haisia.shop.common.domain.DomainConstants.UTC;

@Slf4j
public class UserProfileDomainServiceImpl implements UserProfileDomainService {

  @Override
  public UserProfileCreatedEvent validateAndInitiate(UserProfile userProfile) {
    log.info("UserProfile 이 초기화 되었습니다. id: {}", userProfile.getId().getValue());
    UserProfileInitializer initializer = new UserProfileInitializer(userProfile, UserProfileDomainException::new);
    initializer.validateAndInitialize();

    return new UserProfileCreatedEvent(userProfile, ZonedDateTime.now(ZoneId.of(UTC)));
  }

  @Override
  public void awardPointForFirstLoginToday(UserProfile userProfile) {
    LocalDate today = LocalDate.now(ZoneId.of(UTC));

    if (alreadyAwardedPointForFirstLoginToday(userProfile, today)) {
      throw new UserProfileDomainException("오늘은 출석 포인트를 이미 지급받았습니다. id: " + userProfile.getId().getValue());
    }

    userProfile.increaseBalance(new Money(BigDecimal.valueOf(200)), LedgerReason.LOGGED_IN_FIRST_TODAY);
  }

  private boolean alreadyAwardedPointForFirstLoginToday(UserProfile userProfile, LocalDate today) {
    return userProfile.getLedgers().stream()
      .map(ledger -> ZonedDateTime.ofInstant(ledger.getProcessedAt(), ZoneId.of(UTC)).toLocalDate())
      .anyMatch(today::equals);
  }
}
