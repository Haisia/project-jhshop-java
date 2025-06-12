package com.haisia.shop.catalog.service.domain.service;

import com.haisia.shop.catalog.service.domain.dto.create.CreateProductCommand;
import com.haisia.shop.catalog.service.domain.dto.create.CreateProductResponse;
import com.haisia.shop.catalog.service.domain.mapper.ProductDataMapper;
import com.haisia.shop.catalog.service.domain.ports.input.application.ProductApplicationService;
import com.haisia.shop.catalog.service.domain.ports.output.repository.ProductRepository;
import com.haisia.shop.catalog.service.domain.ports.output.repository.SellerRepository;
import com.haisia.shop.catalog.service.domain.product.ProductDomainService;
import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.catalog.service.domain.seller.exception.SellerNotFoundException;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {

  private final ProductDomainService domainService;
  private final ProductDataMapper mapper;
  private final ProductRepository repository;

  private final SellerRepository sellerRepository;

  @Override
  public CreateProductResponse create(CreateProductCommand command, UserSession userSession) {
    Seller seller = sellerRepository.findByUserAuthId(new UserAuthId(UUID.fromString(userSession.userAuthId())))
      .orElseThrow(() -> new SellerNotFoundException("존재하지 않는 Seller 입니다. userAuthId: " + userSession.userAuthId()));

    Product createdProduct = mapper.createProductCommandToProduct(command, seller.getId());
    domainService.validateAndInitiate(createdProduct);

    Product savedProduct = repository.save(createdProduct);
    return new CreateProductResponse(savedProduct.getId().getValue());
  }
}
