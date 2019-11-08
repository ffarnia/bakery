package domain;

import java.util.List;

/**
 * @author Created by Fazel on 11/6/2019.
 * <p>This model entity is for request order with a list of Order Items</p>
 */
public class Order {
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
