package com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.repository;

import com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.entity.RefreshTokenRecordJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRecordJpaRepository extends JpaRepository<RefreshTokenRecordJpaEntity, UUID> {
}
