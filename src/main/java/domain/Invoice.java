package domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Fazel on 11/7/2019.
 */
public class Invoice {

    private BigDecimal totalPriceItem;
    private OrderItem orderItem;
    private Map<Pack,Long> packagingMap;

    public Invoice(BigDecimal totalPriceItem, OrderItem orderItem, Map<Pack, Long> packagingMap) {
        this.totalPriceItem = totalPriceItem;
        this.orderItem = orderItem;
        this.packagingMap = packagingMap;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "orderItem=" + orderItem +
                ", totalPriceItem=" + totalPriceItem +
                ", packagingMap=" + packagingMap +
                '}';
    }

    public BigDecimal getTotalPriceItem() {
        return totalPriceItem;
    }

    public void setTotalPriceItem(BigDecimal totalPriceItem) {
        this.totalPriceItem = totalPriceItem;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Map<Pack, Long> getPackagingMap() {
        return packagingMap;
    }

    public void setPackagingMap(Map<Pack, Long> packagingMap) {
        this.packagingMap = packagingMap;
    }
}
