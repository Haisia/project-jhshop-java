package com.haisia.shop.auth.service.domain.handler;

import com.haisia.shop.auth.service.domain.dto.update.UpdatePasswordCommand;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserAuthRepository;
import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import com.haisia.shop.auth.service.domain.userauth.exception.UserAuthDomainException;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UpdatePasswordCommandHandler {

  private final UserAuthRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void updatePassword(UpdatePasswordCommand command, UserSession userSession) {
    UserAuth userAuth = repository.findById(new UserAuthId(UUID.fromString(userSession.userAuthId())))
      .orElseThrow(() -> new UserAuthDomainException("로그인 정보가 올바르지 않습니다."));

    userAuth.changePassword(passwordEncoder.encode(command.newPassword()));
  }

}
