package com.haisia.shop.common.dataaccess.jpa.outbox.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.haisia.shop.common.domain.entity.BaseEntity;
import com.haisia.shop.common.domain.saga.SagaAction;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "outbox_message")
@Entity
public class OutboxMessageJpaEntity extends BaseEntity<Long> {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @Column(nullable = false)
  private UUID sagaId;

  @Column(nullable = false)
  private UUID aggregateId;

  @Column(nullable = false)
  private String aggregateType;

  @Column(nullable = false)
  private String eventName;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private SagaAction action;

  @Type(JsonType.class)
  @Column(columnDefinition = "json")
  private JsonNode payload;
}
