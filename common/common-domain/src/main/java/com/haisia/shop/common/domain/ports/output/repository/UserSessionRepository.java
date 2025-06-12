package com.haisia.shop.common.domain.ports.output.repository;

import com.haisia.shop.common.domain.valueobject.UserSession;

import java.util.Optional;

public interface UserSessionRepository {
  UserSession save(UserSession userSession);
  Optional<UserSession> findById(String key);

}
