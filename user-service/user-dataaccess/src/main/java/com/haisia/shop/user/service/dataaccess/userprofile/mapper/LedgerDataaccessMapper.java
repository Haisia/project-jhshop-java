package com.haisia.shop.user.service.dataaccess.userprofile.mapper;

import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.user.service.dataaccess.userprofile.entity.LedgerJpaEntity;
import com.haisia.shop.user.service.domain.entity.Ledger;
import org.springframework.stereotype.Component;

@Component
public class LedgerDataaccessMapper {

  public Ledger toPojo(LedgerJpaEntity jpaEntity) {
    return Ledger.builder()
      .mark(jpaEntity.getMark())
      .change(new Money(jpaEntity.getChange()))
      .before(new Money(jpaEntity.getBefore()))
      .reason(jpaEntity.getReason())
      .processedAt(jpaEntity.getProcessedAt())
      .build();
  }

  public LedgerJpaEntity toJpaEntity(Ledger ledger) {
    return LedgerJpaEntity.builder()
      .id(ledger.getId())
      .mark(ledger.getMark())
      .change(ledger.getChange().amount())
      .before(ledger.getBefore().amount())
      .current(ledger.getCurrent().amount())
      .reason(ledger.getReason())
      .processedAt(ledger.getProcessedAt())
      .build();
  }

}
