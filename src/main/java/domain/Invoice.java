package domain;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Fazel on 11/7/2019.
 */
public class Invoice {

    private BigDecimal totalPriceItem;
    private OrderItem orderItem;
    private Map<Pack, Long> packagingMap;
    private String message;

    public Invoice(BigDecimal totalPriceItem, OrderItem orderItem, Map<Pack, Long> packagingMap) {
        this.totalPriceItem = totalPriceItem;
        this.orderItem = orderItem;
        this.packagingMap = packagingMap;
    }

    public BigDecimal getTotalPriceItem() {
        return totalPriceItem;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public Map<Pack, Long> getPackagingMap() {
        return packagingMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        if (!getTotalPriceItem().equals(invoice.getTotalPriceItem())) return false;
        if (!getOrderItem().equals(invoice.getOrderItem())) return false;
        if (!getPackagingMap().equals(invoice.getPackagingMap())) return false;
        return getMessage().equals(invoice.getMessage());

    }

    @Override
    public int hashCode() {
        int result = getTotalPriceItem().hashCode();
        result = 31 * result + getOrderItem().hashCode();
        result = 31 * result + getPackagingMap().hashCode();
        result = 31 * result + getMessage().hashCode();
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "orderItem=" + orderItem +
                ", totalPriceItem=" + totalPriceItem +
                ", packagingMap=" + packagingMap +
                ", message=" + message +
                '}';
    }


}
