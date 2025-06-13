package com.haisia.shop.order.service.domain.entity;

import com.haisia.shop.common.domain.AggregateRootInitializer;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.order.service.domain.exception.OrderDomainException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OrderInitializer extends AggregateRootInitializer<Order, OrderDomainException> {

  public OrderInitializer(Order target, Function<String, OrderDomainException> exceptionFactory) {
    super(target, exceptionFactory);
  }

  @Override
  protected void executeValidations(Map<String, String> errors) {
    validateOrderItemsAndPrice(errors);
    validateBuyer(errors);
    validateSeller(errors);
    validateAddress(errors);
    validateTrackingId(errors);
  }

  @Override
  protected void doInitialize() {
    target.initialize();
  }

  private void validateOrderItemsAndPrice(Map<String, String> errors) {
    List<OrderItem> orderItems = target.getOrderItems();
    if (orderItems == null || orderItems.isEmpty()) {
      errors.put("orderItems", "orderItems 가 null 이거나 비어있습니다.");
      return;
    }

    if (target.getPrice() == null || target.getPrice().getAmount() == null) {
      errors.put("price", NULL);
      return;
    }

    Money totalPrice = orderItems.stream()
      .map(orderItem -> {
        Money price = orderItem.getPrice();
        Money subTotal = orderItem.getSubTotal();
        int quantity = orderItem.getQuantity();

        if (price == null) {
          errors.put("order.orderItem.price", NULL);
          return Money.ZERO;
        }
        if (subTotal == null) {
          errors.put("order.orderItem.subTotal", NULL);
          return Money.ZERO;
        }
        if (quantity <= 0) {
          errors.put("order.orderItem.quantity", "0보다 커야합니다.");
          return Money.ZERO;
        }
        if (!price.multiply(quantity).equals(subTotal)) {
          String message = String.format("기대값과 다릅니다. expect: %s result: %s", price.multiply(quantity), subTotal);
          errors.put("order.orderItem.subTotal", message);
          return Money.ZERO;
        }
        return subTotal;
      })
      .reduce(Money.ZERO, Money::add);

    if (!totalPrice.equals(target.getPrice())) {
      String message = String.format("price 가 예상과 다릅니다. expect: %s result: %s", totalPrice, target.getPrice());
      errors.put("price", message);
    }
  }

  private void validateBuyer(Map<String, String> errors) {
    super.validateBaseId(errors, target.getBuyer(), "buyer");
  }

  private void validateSeller(Map<String, String> errors) {
    super.validateBaseId(errors, target.getSeller(), "seller");
  }

  private void validateAddress(Map<String, String> errors) {
    super.validateAddress(errors, target.getDeliveryAddress(), "deliveryAddress");
  }

  private void validateTrackingId(Map<String, String> errors) {
    super.validateBaseId(errors, target.getTrackingId(), "trackingId");
  }
}
