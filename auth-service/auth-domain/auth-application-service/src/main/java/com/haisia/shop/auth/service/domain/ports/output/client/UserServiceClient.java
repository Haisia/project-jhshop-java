package com.haisia.shop.auth.service.domain.ports.output.client;

import com.haisia.shop.auth.service.domain.userauth.event.UserAuthCreatedEvent;

public interface UserServiceClient {
  void createUserProfile(UserAuthCreatedEvent event);
}
