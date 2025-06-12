package com.haisia.shop.catalog.service.domain.service;

import com.haisia.shop.catalog.service.domain.dto.create.CreateSellerCommand;
import com.haisia.shop.catalog.service.domain.ports.input.application.SellerApplicationService;
import com.haisia.shop.catalog.service.domain.ports.output.repository.SellerRepository;
import com.haisia.shop.catalog.service.domain.seller.SellerDomainService;
import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.catalog.service.domain.seller.exception.SellerDomainException;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SellerApplicationServiceImpl implements SellerApplicationService {

  private final SellerDomainService domainService;
  private final SellerRepository repository;

  @Override
  public void create(CreateSellerCommand command) {
    boolean alreadyRegistered = repository.findByUserAuthId(new UserAuthId(command.userAuthId())).isPresent();
    if (alreadyRegistered) {
      throw new SellerDomainException("이미 Seller로 등록된 유저입니다. userAuthId: " + command.userAuthId());
    }

    Seller createdSeller = Seller.builder()
      .userAuthId(new UserAuthId(command.userAuthId()))
      .build();

    domainService.validateAndInitiate(createdSeller);
    repository.save(createdSeller);
  }
}
