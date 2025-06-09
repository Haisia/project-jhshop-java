package com.haisia.shop.auth.service.domain;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.auth.service.domain.event.UserAuthCreatedEvent;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;

public interface UserAuthDomainService {

  UserAuthCreatedEvent validateAndInitiateUserAuth(UserAuth userAuth, Address address, PhoneNumber phoneNumber);

}
