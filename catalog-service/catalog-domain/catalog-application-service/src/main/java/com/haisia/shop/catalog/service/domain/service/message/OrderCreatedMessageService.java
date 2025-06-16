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
import com.haisia.shop.common.domain.valueobject.Stock;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedMessageService implements OrderCreatedUsecase {

  private final ProductRepository productRepository;

  @Transactional
  @SagaFailureStatus(SagaStatus.COMPENSATING)
  @Override
  public void process(OrderCreatedEventPayload payload) {
    /*
     * 1. seller 검증
     * 2. product 검증
     * 3. price 검증
     * 4. 주문에 따른 product 재고 감소
     * */

    Map<ProductId, Product> findProductsMap = productRepository.findAllByIds(
      payload.getOrderItems().stream().map(item -> new ProductId(item.productId())).toList()
    );

    payload.getOrderItems()
      .forEach(item -> {
        Product product = findProductsMap.get(new ProductId(item.productId()));
        if (product == null) {
          throw new DomainException("[catalog-service]Product 를 찾을 수 없습니다. productId: " + item.productId());
        }

        if (!product.getPrice().equals(new Money(item.price()))) {
          throw new DomainException(
            String.format(
              "[catalog-service]OrderItem.price 가 기대값과 다릅니다. orderItem.price: %s, product.price: %s",
              item.price(),
              product.getPrice()
            )
          );
        }

        if (product.getStock().isLessThan(new Stock(item.quantity()))) {
          throw new DomainException(
            String.format(
              "[catalog-service]Product 의 재고가 Order 보다 적습니다. product.stock : %s, order.quantity : %s",
              product.getStock(),
              item.quantity())
          );
        }
      });

    payload.getOrderItems().forEach(item -> {
      Product product = findProductsMap.get(new ProductId(item.productId()));
      product.decreaseStock(new Stock(item.quantity()));
    });
  }

  @Override
  public void rollback(OrderCreatedEventPayload payload) {
    log.debug("[catalog-service]OrderCreatedEventPayload 의 rollback 메세지를 수신했지만, 아무것도 하지 않음.");
  }
}
