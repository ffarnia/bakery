package domain;

import java.util.List;

/**
 * Created by Fazel on 11/6/2019.
 */
public class OrderItem {

    private Integer amout;
    private Product product;

    public OrderItem(Integer amout, Product product) {
        this.amout = amout;
        this.product = product;
    }

    public Integer getAmout() {
        return amout;
    }

    public void setAmout(Integer amout) {
        this.amout = amout;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
