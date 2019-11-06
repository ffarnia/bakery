package domain;

import java.math.BigDecimal;

/**
 * Created by Fazel on 11/6/2019.
 */
public class Pack {

    private Integer quantity;
    private BigDecimal price;

    public Pack(Integer quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
