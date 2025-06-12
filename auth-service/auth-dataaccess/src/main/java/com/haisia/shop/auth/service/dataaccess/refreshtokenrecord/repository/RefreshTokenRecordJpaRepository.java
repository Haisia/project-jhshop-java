package com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.repository;

import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;
import com.haisia.shop.common.domain.valueobject.id.RefreshTokenRecordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRecordJpaRepository extends JpaRepository<RefreshTokenRecord, RefreshTokenRecordId> {
  Optional<RefreshTokenRecord> findByToken(String token);
}
