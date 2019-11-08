package service;

import domain.Pack;
import domain.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Created by Fazel on 11/7/2019.
 *         <p>Create products and store in repository</p>
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

    /**
     * <p>Find a unique product base on given product code </p>
     *
     * @param productCode
     * @return product else null
     */
    @Override
    public Product findProductByCode(String productCode) {
        Optional<Product> productOptional = repositoryProducts.stream().filter(product -> product.getCode().equals(productCode)).findFirst();
        return productOptional.orElse(null);
    }

    /**
     * <p>Sort descending list of packs base on quantity of every product </p>
     *
     * @param product
     * @return product with sorted packs
     */
    @Override
    public Product sortProductPacks(Product product) {
        List<Pack> packList = product.getPacks();
        List<Pack> sortedPacks = packList.stream().sorted(Comparator.comparingInt(Pack::getQuantity).reversed()).collect(Collectors.toList());
        product.setPacks(sortedPacks);
        return product;
    }
}
