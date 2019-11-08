package service;

import domain.Order;
import domain.OrderItem;

import java.util.List;

/**
 * Created by Fazel on 11/8/2019.
 */
public interface OrderService {

    Order createOrder(List<OrderItem> orderItems);

    OrderItem createOrderItem(Integer quantity,String productCode);
}
