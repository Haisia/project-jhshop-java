package com.haisia.shop.order.service.domain.mapper;

import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.order.service.domain.dto.create.CreateOrderCommand;
import com.haisia.shop.order.service.domain.entity.Order;
import com.haisia.shop.order.service.domain.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderDataMapper {

  public Order toOrder(CreateOrderCommand command, UserSession userSession) {
    Order order = Order.builder()
      .price(new Money(command.price()))
      .buyer(new UserAuthId(UUID.fromString(userSession.userAuthId())))
      .seller(new UserAuthId(command.seller()))
      .deliveryAddress(command.deliveryAddress())
      .build();

    command.orderItems().forEach(orderItem -> toOrderItem(orderItem, order));

    return order;
  }

  private OrderItem toOrderItem(CreateOrderCommand.Item item, Order order) {
    return order.addItem(
      new ProductId(item.productId()),
      item.quantity(),
      new Money(item.price()),
      new Money(item.subTotal())
    );
  }

  // ---

}
