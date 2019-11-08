import domain.Order;
import domain.OrderItem;
import domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.OrderService;
import service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fazel on 11/8/2019.
 */
public class OrderServiceTest {

    private OrderService orderService;

    @Before
    public void init() {
        orderService = new OrderServiceImpl();
    }

    @Test
    public void createOrderItem() {
        OrderItem orderItemCreatedByService = orderService.createOrderItem(20, "VS5");
        OrderItem expectedOrderItem = createExpectedOrderItem(20,"VS5");
        Assert.assertNotNull(orderItemCreatedByService);
        Assert.assertEquals(orderItemCreatedByService,expectedOrderItem);
    }

    @Test
    public void createOrderWithGivenOrderItems() {
        List<OrderItem> createdOrderItems = new ArrayList<>();
        createdOrderItems.add(orderService.createOrderItem(10, "VS5"));
        createdOrderItems.add(orderService.createOrderItem(14, "MB11"));
        createdOrderItems.add(orderService.createOrderItem(13, "CF"));
        Order createdOrderByService = orderService.createOrder(createdOrderItems);
        Order expectedOrder = createExpectedOrder();
        Assert.assertFalse(createdOrderByService.getOrderItems().isEmpty());
        Assert.assertArrayEquals(createdOrderByService.getOrderItems().toArray(),expectedOrder.getOrderItems().toArray());

    }

    private OrderItem createExpectedOrderItem(Integer amount,String productCode) {
        return new OrderItem(amount,new Product(productCode));
    }

    private Order createExpectedOrder() {
        List<OrderItem> expectedOrderItems = new ArrayList<>();
        expectedOrderItems.add(createExpectedOrderItem(10, "VS5"));
        expectedOrderItems.add(createExpectedOrderItem(14, "MB11"));
        expectedOrderItems.add(createExpectedOrderItem(13, "CF"));
        return new Order(expectedOrderItems);
    }

}
