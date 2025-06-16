package com.haisia.shop.common.dataaccess.jpa.sagaevent.entity;

import com.haisia.shop.common.domain.entity.BaseEntity;
import com.haisia.shop.common.domain.saga.SagaAction;
import com.haisia.shop.common.domain.saga.SagaStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "saga_event")
@Entity
public class SagaEventJpaEntity extends BaseEntity<UUID> {
  @Id
  private UUID id;
  private UUID aggregateId;
  private String aggregateType;
  private String eventName;
  @Enumerated(EnumType.STRING)
  private SagaStatus status;
  @Enumerated(EnumType.STRING)
  private SagaAction action;
  private String failureMessages;
}
