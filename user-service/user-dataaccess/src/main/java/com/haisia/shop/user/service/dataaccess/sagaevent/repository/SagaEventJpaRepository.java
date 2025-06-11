package com.haisia.shop.user.service.dataaccess.sagaevent.repository;

import com.haisia.shop.user.service.dataaccess.sagaevent.entity.SagaEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SagaEventJpaRepository extends JpaRepository<SagaEventJpaEntity, UUID> {
}
