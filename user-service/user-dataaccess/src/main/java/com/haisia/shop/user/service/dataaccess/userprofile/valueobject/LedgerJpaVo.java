package com.haisia.shop.user.service.dataaccess.userprofile.valueobject;

import com.haisia.shop.user.service.domain.valueobject.Mark;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class LedgerJpaVo {
  private BigDecimal before;
  private BigDecimal current;
  private Mark mark;
  private BigDecimal change;
  private String reason;
  private Instant processedAt;
}
