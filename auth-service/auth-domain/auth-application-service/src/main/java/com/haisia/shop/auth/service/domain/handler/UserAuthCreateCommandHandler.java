package com.haisia.shop.auth.service.domain.handler;

import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.auth.service.domain.dto.register.RegisterUserResponse;
import com.haisia.shop.auth.service.domain.event.UserAuthCreatedEvent;
import com.haisia.shop.auth.service.domain.handler.helper.UserAuthCreateHelper;
import com.haisia.shop.auth.service.domain.handler.helper.UserProfileHelper;
import com.haisia.shop.auth.service.domain.mapper.UserAuthDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserAuthCreateCommandHandler {

  private final UserAuthCreateHelper createHelper;
  private final UserAuthDataMapper mapper;
  private final UserProfileHelper userProfileHelper;


  @Transactional
  public RegisterUserResponse registerUser(RegisterUserCommand command) {
    UserAuthCreatedEvent event = createHelper.hashPasswordAndPersist(command);
    userProfileHelper.createUserProfile(command, event.getUserAuth().getId().getValue());

    RegisterUserResponse response = RegisterUserResponse.from(event.getUserAuth());
    return response;
  }

}
