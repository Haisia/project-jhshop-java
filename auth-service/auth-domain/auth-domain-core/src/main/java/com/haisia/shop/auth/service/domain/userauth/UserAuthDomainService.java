package com.haisia.shop.auth.service.domain.userauth;

import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import com.haisia.shop.auth.service.domain.userauth.event.UserAuthCreatedEvent;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;

public interface UserAuthDomainService {

  UserAuthCreatedEvent validateAndInitiate(UserAuth userAuth, Address address, PhoneNumber phoneNumber);

}
