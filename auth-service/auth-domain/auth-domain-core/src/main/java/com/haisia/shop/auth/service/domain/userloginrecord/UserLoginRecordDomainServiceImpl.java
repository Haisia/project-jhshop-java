package com.haisia.shop.auth.service.domain.userloginrecord;

import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecord;
import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecordInitializer;
import com.haisia.shop.auth.service.domain.userloginrecord.exception.UserLoginRecordDomainException;

public class UserLoginRecordDomainServiceImpl implements UserLoginRecordDomainService {

  @Override
  public Object validateAndInitiate(UserLoginRecord domain) {
    UserLoginRecordInitializer initializer = new UserLoginRecordInitializer(domain, UserLoginRecordDomainException::new);
    initializer.validateAndInitialize();
    return null;
  }
}
