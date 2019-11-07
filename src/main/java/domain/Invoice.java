package domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Fazel on 11/6/2019.
 */
public class Invoice {

    private Integer totalAmount;
    private Product product;
    private List<Pack> packs;

    public Invoice(Integer totalAmount, Product product) {
        this.totalAmount = totalAmount;
        this.product = product;
    }

    public void generateInvoice(Order order,Product product) {
        List<OrderItem> orderItems =  order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            int amount = orderItem.getAmout();

        }
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }
}
