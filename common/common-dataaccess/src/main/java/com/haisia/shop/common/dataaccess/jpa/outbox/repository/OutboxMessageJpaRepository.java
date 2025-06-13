package com.haisia.shop.common.dataaccess.jpa.outbox.repository;

import com.haisia.shop.common.dataaccess.jpa.outbox.entity.OutboxMessageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxMessageJpaRepository extends JpaRepository<OutboxMessageJpaEntity, Long> {
}
