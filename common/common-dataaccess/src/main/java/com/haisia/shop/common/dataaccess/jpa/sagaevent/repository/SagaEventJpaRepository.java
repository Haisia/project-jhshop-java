package com.haisia.shop.common.dataaccess.jpa.sagaevent.repository;

import com.haisia.shop.common.dataaccess.jpa.sagaevent.entity.SagaEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SagaEventJpaRepository extends JpaRepository<SagaEventJpaEntity, UUID> {
}
