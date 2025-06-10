package com.haisia.shop.user.service.domain;

import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.event.UserProfileCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.haisia.shop.common.domain.DomainConstants.UTC;

@Slf4j
public class UserProfileDomainServiceImpl implements UserProfileDomainService {

  @Override
  public UserProfileCreatedEvent validateAndInitiate(UserProfile userProfile) {
    userProfile.validate();
    userProfile.initialize();
    log.info("UserProfile 이 초기화 되었습니다. id: {}", userProfile.getId().getValue());

    return new UserProfileCreatedEvent(userProfile, ZonedDateTime.now(ZoneId.of(UTC)));
  }
}
