package com.haisia.shop.auth.service.domain.handler;

import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthResponse;
import com.haisia.shop.auth.service.domain.handler.helper.UserAuthCreateHelper;
import com.haisia.shop.auth.service.domain.ports.output.client.UserServiceClient;
import com.haisia.shop.auth.service.domain.userauth.event.UserAuthCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateUserAuthCommandHandler {

  private final UserAuthCreateHelper createHelper;
  private final UserServiceClient userServiceClient;


  @Transactional
  public CreateUserAuthResponse create(CreateUserAuthCommand command) {
    UserAuthCreatedEvent event = createHelper.hashPasswordAndPersist(command);
    userServiceClient.createUserProfile(event);

    CreateUserAuthResponse response = CreateUserAuthResponse.from(event.getUserAuth());
    return response;
  }

}
