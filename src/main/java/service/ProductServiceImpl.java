package service;

import domain.Pack;
import domain.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Fazel on 11/7/2019.
 */
public class ProductServiceImpl implements ProductService {

    private List<Product> repositoryProducts;

    public ProductServiceImpl() {
        this.repositoryProducts = new ArrayList<>();
    }


    @Override
    public List<Product> loadAllProducts() {
        return repositoryProducts;
    }

    @Override
    public void addProduct(Product product) {
        repositoryProducts.add(product);
    }

    @Override
    public Product findProductByCode(String productCode) {
        Optional<Product> productOptional = repositoryProducts.stream().filter(product -> product.getCode().equals(productCode)).findFirst();
        return productOptional.orElse(null);
    }

    @Override
    public Product sortProductPacks(Product product) {
        List<Pack> packList = product.getPacks();
        List<Pack> sortedPacks= packList.stream().sorted(Comparator.comparingInt(Pack::getQuantity).reversed()).collect(Collectors.toList());
        product.setPacks(sortedPacks);
        return product;
    }
}
