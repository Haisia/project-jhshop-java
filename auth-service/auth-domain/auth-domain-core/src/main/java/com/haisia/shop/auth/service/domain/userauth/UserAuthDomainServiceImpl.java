package com.haisia.shop.auth.service.domain.userauth;

import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import com.haisia.shop.auth.service.domain.userauth.event.UserAuthCreatedEvent;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.haisia.shop.common.domain.DomainConstants.UTC;

@Slf4j
public class UserAuthDomainServiceImpl implements UserAuthDomainService {

  @Override
  public UserAuthCreatedEvent validateAndInitiate(UserAuth userAuth, Address address, PhoneNumber phoneNumber) {
    userAuth.validate();
    userAuth.initialize();
    log.info("UserAuth 가 초기화 되었습니다. id: {}", userAuth.getId().getValue());

    return new UserAuthCreatedEvent(
      userAuth,
      ZonedDateTime.now(ZoneId.of(UTC)),
      address,
      phoneNumber
    );
  }
}
