package com.haisia.shop.common.dataaccess.jpa.mapper;

import com.haisia.shop.common.dataaccess.jpa.valueobject.AddressJpaVo;
import com.haisia.shop.common.domain.valueobject.Address;
import org.springframework.stereotype.Component;

@Component
public class CommonDataaccessMapper {

  public AddressJpaVo addressToAddressJpaVo(Address address) {
    return AddressJpaVo.builder()
      .country(address.country())
      .city(address.city())
      .street(address.street())
      .zipCode(address.zipCode())
      .detail(address.detail())
      .build();
  }

  public Address addressJpaVoToAddress(AddressJpaVo addressJpaVo) {
    return Address.builder()
      .country(addressJpaVo.getCountry())
      .city(addressJpaVo.getCity())
      .street(addressJpaVo.getStreet())
      .zipCode(addressJpaVo.getZipCode())
      .detail(addressJpaVo.getDetail())
      .build();
  }
}
