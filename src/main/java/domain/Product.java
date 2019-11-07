package domain;

import java.util.List;
import java.util.Set;

/**
 * Created by Fazel on 11/6/2019.
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

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
