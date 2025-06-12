package com.haisia.shop.user.service.domain;

import com.haisia.shop.common.domain.DomainService;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.event.UserProfileCreatedEvent;

public interface UserProfileDomainService extends DomainService<UserProfile> {

  UserProfileCreatedEvent validateAndInitiate(UserProfile userProfile);

  void awardPointForFirstLoginToday(UserProfile userProfile);

}
