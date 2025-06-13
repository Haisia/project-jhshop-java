package com.haisia.shop.catalog.service.domain.service.message;

import com.haisia.shop.catalog.service.domain.ports.input.message.OrderCreatedUsecase;
import com.haisia.shop.catalog.service.domain.ports.output.repository.ProductRepository;
import com.haisia.shop.catalog.service.domain.ports.output.repository.SellerRepository;
import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.catalog.service.domain.seller.entity.Seller;
import com.haisia.shop.common.domain.event.payload.OrderCreatedEventPayload;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.common.domain.saga.SagaFailureStatus;
import com.haisia.shop.common.domain.saga.SagaStatus;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OrderCreatedMessageService implements OrderCreatedUsecase {

  private final SellerRepository sellerRepository;
  private final ProductRepository productRepository;

  @Transactional
  @SagaFailureStatus(SagaStatus.COMPENSATING)
  @Override
  public void process(OrderCreatedEventPayload payload) {
    /*
     * 1. seller 검증
     * 2. product 검증
     * 3. price 검증
     * */

    Optional<Seller> optionalSeller = sellerRepository.findByUserAuthId(new UserAuthId(payload.getSellerUserAuthId()));
    if (optionalSeller.isEmpty()) {
      throw new DomainException("Seller 를 찾을 수 없습니다. sellerUserAuthId: " + payload.getSellerUserAuthId());
    }

    payload.getOrderItems()
      .forEach(item -> {
        Product product = productRepository.findById(new ProductId(item.productId()))
          .orElseThrow(() -> new DomainException("Product 를 찾을 수 없습니다. productId: " + item.productId()));
        if (!product.getPrice().equals(new Money(item.price()))) {
          throw new DomainException(
            String.format(
              "OrderItem.price 가 기대값과 다릅니다. item.price: %s, product.price: %s",
              item.price(),
              product.getPrice()
            )
          );
        }
      });
  }

  @Override
  public void rollback(OrderCreatedEventPayload payload) {

  }

}
