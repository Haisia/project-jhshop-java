package com.haisia.shop.auth.service.dataaccess.userauth.repository;

import com.haisia.shop.auth.service.dataaccess.userauth.entity.UserAuthJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAuthJpaRepository extends JpaRepository<UserAuthJpaEntity, UUID> {
}
