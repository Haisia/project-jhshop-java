package com.haisia.shop.auth.service.dataaccess.userauth.repository;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthJpaRepository extends JpaRepository<UserAuth, UUID> {
  Optional<UserAuth> findByEmail(String email);
}
