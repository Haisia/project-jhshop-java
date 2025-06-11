package com.haisia.shop.auth.service.dataaccess.outbox.repository;

import com.haisia.shop.auth.service.dataaccess.outbox.entity.OutboxMessageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxMessageJpaRepository extends JpaRepository<OutboxMessageJpaEntity, Long> {
}
