package service;

import domain.Order;
import domain.OrderItem;
import domain.Product;

import java.util.List;

/**
 * @author Created by Fazel on 11/8/2019.
 *         <p>Create order requested with list of orderItems</p>
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(List<OrderItem> orderItems) {
        return new Order(orderItems);
    }

    @Override
    public OrderItem createOrderItem(Integer quantity, String productCode) {
        return new OrderItem(quantity, new Product(productCode));
    }

}
