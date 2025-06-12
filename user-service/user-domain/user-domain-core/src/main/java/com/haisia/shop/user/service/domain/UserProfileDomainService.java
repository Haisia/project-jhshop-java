package com.haisia.shop.user.service.domain;

import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.event.UserProfileCreatedEvent;

public interface UserProfileDomainService {

  UserProfileCreatedEvent validateAndInitiate(UserProfile userProfile);

  void awardPointForFirstLoginToday(UserProfile userProfile);

}
