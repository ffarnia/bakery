package domain;

import java.math.BigDecimal;

/**
 * @author Created by Fazel on 11/6/2019.
 * <p>This model entity is for packing every product into desire packages</p>
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
