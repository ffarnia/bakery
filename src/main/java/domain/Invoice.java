package domain;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Fazel on 11/6/2019.
 */
public class Invoice {

    private BigDecimal totalAmount;
    private Product product;
    private Set<Pack> packs;

    public Invoice(BigDecimal totalAmount, Product product, Set<Pack> packs) {
        this.totalAmount = totalAmount;
        this.product = product;
        this.packs = packs;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Pack> getPacks() {
        return packs;
    }

    public void setPacks(Set<Pack> packs) {
        this.packs = packs;
    }
}
