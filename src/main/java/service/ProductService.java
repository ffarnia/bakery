package service;

import domain.Product;

import java.util.List;

/**
 * @author Created by Fazel on 11/6/2019.
 */
public interface ProductService {

    List<Product> loadAllProducts();

    void addProduct(Product product);

    Product findProductByCode(String productCode);

    Product sortProductPacks(Product product);

}
