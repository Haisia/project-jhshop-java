package com.haisia.shop.auth.service.dataaccess.userauth.repository;

import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthJpaRepository extends JpaRepository<UserAuth, UserAuthId> {
  Optional<UserAuth> findByEmail(String email);
}
