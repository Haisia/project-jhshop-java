package com.haisia.shop.common.dataaccess.jpa.sagaevent.repository;

import com.haisia.shop.common.dataaccess.jpa.sagaevent.entity.SagaEventJpaEntity;
import com.haisia.shop.common.domain.saga.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface SagaEventJpaRepository extends JpaRepository<SagaEventJpaEntity, UUID> {
  List<SagaEventJpaEntity> findAllByStatus(SagaStatus status);
}
