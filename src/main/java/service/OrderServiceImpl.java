package service;

import domain.Order;
import domain.OrderItem;
import domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fazel on 11/8/2019.
 */
public class OrderServiceImpl implements OrderService{
    @Override
    public Order createOrder(List<OrderItem> orderItems) {
        return new Order(orderItems);
    }

    @Override
    public OrderItem createOrderItem(Integer quantity, String productCode) {
        return new OrderItem(quantity,new Product(productCode));
    }

}
