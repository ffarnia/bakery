package domain;

import java.util.List;
import java.util.Set;

/**
 * @author Created by Fazel on 11/6/2019.
 * <p>This model entity provide information about available products in repository and can be chosen in Oredrs</p>
 */
public class Product {

    private String name;
    private String code;
    private List<Pack> packs;

    public Product(String name, String code, List<Pack> packs) {
        this.name = name;
        this.code = code;
        this.packs = packs;
    }

    public Product(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", packs=" + packs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!getName().equals(product.getName())) return false;
        if (!getCode().equals(product.getCode())) return false;
        return getPacks().equals(product.getPacks());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getCode().hashCode();
        result = 31 * result + getPacks().hashCode();
        return result;
    }
}
