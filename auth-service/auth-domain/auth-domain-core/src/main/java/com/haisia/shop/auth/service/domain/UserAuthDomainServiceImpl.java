package com.haisia.shop.auth.service.domain;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.auth.service.domain.event.UserAuthCreatedEvent;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.haisia.shop.common.domain.DomainConstants.UTC;

@Slf4j
public class UserAuthDomainServiceImpl implements UserAuthDomainService {

  @Override
  public UserAuthCreatedEvent validateAndInitiateUserAuth(UserAuth userAuth, Address address, PhoneNumber phoneNumber) {
    userAuth.validate();
    userAuth.initialize();
    log.info("UserAuth with id: {} 가 초기화 되었습니다.", userAuth.getId().getValue());

    return new UserAuthCreatedEvent(
      userAuth,
      ZonedDateTime.now(ZoneId.of(UTC)),
      address,
      phoneNumber
    );
  }
}
