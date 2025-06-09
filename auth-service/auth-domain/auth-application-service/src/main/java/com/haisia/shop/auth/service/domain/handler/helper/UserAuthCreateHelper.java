package com.haisia.shop.auth.service.domain.handler.helper;

import com.haisia.shop.auth.service.domain.UserAuthDomainService;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.auth.service.domain.event.UserAuthCreatedEvent;
import com.haisia.shop.auth.service.domain.exception.UserAuthDomainException;
import com.haisia.shop.auth.service.domain.mapper.UserAuthDataMapper;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserAuthCreateHelper {

  private final PasswordEncoder passwordEncoder;
  private final UserAuthDomainService service;
  private final UserAuthDataMapper mapper;
  private final UserAuthRepository repository;

  /*
  * 1. password 암호화
  * 2. 계정생성
  * */
  @Transactional
  public UserAuthCreatedEvent hashPasswordAndPersist(CreateUserAuthCommand command) {
    UserAuth userAuth = mapper.createUserAuthCommandToUserAuth(command, hashPassword(command.password()));
    UserAuthCreatedEvent event = service.validateAndInitiateUserAuth(userAuth, command.address(), command.phoneNumber());

    repository.save(userAuth).orElseThrow(() -> new UserAuthDomainException("UserAuth 를 저장 할 수 없습니다."));

    log.info("UserAuth 가 생성되었습니다. id: {}", event.getUserAuth().getId().getValue());

    return event;
  }

  private String hashPassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

}
