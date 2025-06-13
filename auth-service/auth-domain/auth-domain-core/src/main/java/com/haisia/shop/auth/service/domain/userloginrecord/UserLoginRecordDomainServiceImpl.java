package com.haisia.shop.auth.service.domain.userloginrecord;

import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecord;
import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecordInitializer;
import com.haisia.shop.auth.service.domain.userloginrecord.exception.UserLoginRecordDomainException;
import com.haisia.shop.common.domain.event.DomainEvent;

public class UserLoginRecordDomainServiceImpl implements UserLoginRecordDomainService {

  @Override
  public DomainEvent validateAndInitiate(UserLoginRecord domain) {
    UserLoginRecordInitializer initializer = new UserLoginRecordInitializer(domain, UserLoginRecordDomainException::new);
    initializer.validateAndInitialize();
    return null;
  }
}
