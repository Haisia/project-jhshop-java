package com.haisia.shop.user.service.dataaccess.sagaevent.entity;

import com.haisia.shop.common.domain.entity.BaseJpaEntity;
import com.haisia.shop.common.domain.saga.SagaStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "saga_event")
@Entity
public class SagaEventJpaEntity extends BaseJpaEntity {
  @Id
  private UUID id;
  private UUID aggregateId;
  private String aggregateType;
  private String eventName;
  private SagaStatus status;
}
