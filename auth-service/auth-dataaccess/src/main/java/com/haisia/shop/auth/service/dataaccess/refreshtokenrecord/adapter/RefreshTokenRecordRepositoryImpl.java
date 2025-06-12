package com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.adapter;

import com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.repository.RefreshTokenRecordJpaRepository;
import com.haisia.shop.auth.service.domain.ports.output.repository.RefreshTokenRecordRepository;
import com.haisia.shop.auth.service.domain.refreshtokenrecord.entity.RefreshTokenRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RefreshTokenRecordRepositoryImpl implements RefreshTokenRecordRepository {

  private final RefreshTokenRecordJpaRepository repository;

  @Override
  public RefreshTokenRecord save(RefreshTokenRecord refreshTokenRecord) {
    return repository.save(refreshTokenRecord);
  }

  @Override
  public Optional<RefreshTokenRecord> findByRefreshToken(String refreshToken) {
    return repository.findByToken(refreshToken);
  }
}
