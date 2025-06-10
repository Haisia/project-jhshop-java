package com.haisia.shop.common.dataaccess.jpa.valueobject;

import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class AddressJpaVo {
  private String country;
  private String city;
  private String street;
  private String zipCode;
  private String detail;
}
