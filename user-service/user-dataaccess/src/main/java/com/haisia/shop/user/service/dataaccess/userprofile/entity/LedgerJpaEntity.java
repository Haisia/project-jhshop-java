package com.haisia.shop.user.service.dataaccess.userprofile.entity;

import com.haisia.shop.user.service.domain.valueobject.LedgerReason;
import com.haisia.shop.user.service.domain.valueobject.Mark;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ledger")
@Entity
public class LedgerJpaEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @JoinColumn(name = "user_profile_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private UserProfileJpaEntity userProfile;
  private Mark mark;
  private BigDecimal change;
  private BigDecimal before;
  private BigDecimal current;
  private LedgerReason reason;
  private Instant processedAt;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LedgerJpaEntity that = (LedgerJpaEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
