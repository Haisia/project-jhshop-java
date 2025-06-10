package com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.adapter;

import com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.entity.RefreshTokenRecordJpaEntity;
import com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.mapper.RefreshTokenRecordDataaccessMapper;
import com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.repository.RefreshTokenRecordJpaRepository;
import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;
import com.haisia.shop.auth.service.domain.ports.output.repository.RefreshTokenRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RefreshTokenRecordRepositoryImpl implements RefreshTokenRecordRepository {

  private final RefreshTokenRecordJpaRepository repository;
  private final RefreshTokenRecordDataaccessMapper mapper;

  @Override
  public RefreshTokenRecord save(RefreshTokenRecord refreshTokenRecord) {
    RefreshTokenRecordJpaEntity jpaEntity = mapper.refreshTokenRecordToRefreshTokenRecordJpaEntity(refreshTokenRecord);
    return mapper.refreshTokenRecordJpaEntityToRefreshTokenRecord(repository.save(jpaEntity));
  }

  @Override
  public Optional<RefreshTokenRecord> findByRefreshToken(String refreshToken) {
    return repository.findByToken(refreshToken).map(mapper::refreshTokenRecordJpaEntityToRefreshTokenRecord);
  }
}
