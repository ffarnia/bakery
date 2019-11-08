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

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pack)) return false;

        Pack pack = (Pack) o;

        if (!getQuantity().equals(pack.getQuantity())) return false;
        return getPrice().equals(pack.getPrice());

    }

    @Override
    public int hashCode() {
        int result = getQuantity().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
