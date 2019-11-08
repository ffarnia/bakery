import domain.*;
import service.InvoiceService;
import service.InvoiceServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Fazel on 11/6/2019.
 */
public class BakeryApplication {
    public static void main(String[] args) {
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem3 = new OrderItem(10,new Product("VS5"));
        OrderItem orderItem2 = new OrderItem(14,new Product("MB11"));
        OrderItem orderItem1 = new OrderItem(3,new Product("CF"));
        orderItems.add(orderItem3);
        orderItems.add(orderItem2);
        orderItems.add(orderItem1);
        Order order = new Order(orderItems);
        ProductService productService = prepareProducts();
        InvoiceService service = new InvoiceServiceImpl(productService);
        service.generateInvoice(order);
    }


    static ProductService prepareProducts() {
        ProductService productService = new ProductServiceImpl();
        List<Pack> packList_vs5 = new ArrayList<Pack>();
        packList_vs5.add(new Pack(3, new BigDecimal("6.99")));
        packList_vs5.add(new Pack(5, new BigDecimal("8.99")));
        Product product_vs5 = new Product("Vegemite Scroll", "VS5", packList_vs5);

        List<Pack> packList_mb11 = new ArrayList<Pack>();
        packList_mb11.add(new Pack(2, new BigDecimal("9.95")));
        packList_mb11.add(new Pack(5, new BigDecimal("16.95")));
        packList_mb11.add(new Pack(8, new BigDecimal("24.95")));
        Product product_mb11 = new Product("Blueberry Muffin", "MB11", packList_mb11);

        List<Pack> packList_cf = new ArrayList<Pack>();
        packList_cf.add(new Pack(5, new BigDecimal("9.95")));
        packList_cf.add(new Pack(9, new BigDecimal("16.99")));
        packList_cf.add(new Pack(3, new BigDecimal("5.95")));
        Product product_cf = new Product("Croissant", "CF", packList_cf);

        productService.addProduct(product_cf);
        productService.addProduct(product_mb11);
        productService.addProduct(product_vs5);

        return productService;

    }
}
