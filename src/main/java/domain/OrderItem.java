package domain;

/**
 * @author Created by Fazel on 11/6/2019.
 * <p>This model entity is for creation of order details in final order</p>
 */
public class OrderItem {

    private Integer amount;
    private Product product;

    public OrderItem(Integer amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "amount=" + amount +
                ", product=" + product.getCode() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (!getAmount().equals(orderItem.getAmount())) return false;
        return getProduct().getCode().equals(orderItem.getProduct().getCode());

    }

    @Override
    public int hashCode() {
        int result = getAmount().hashCode();
        result = 31 * result + getProduct().hashCode();
        return result;
    }
}
