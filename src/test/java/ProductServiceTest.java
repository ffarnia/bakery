import domain.Pack;
import domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ProductService;
import service.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fazel on 11/8/2019.
 */
public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void init() {
        this.productService = new ProductServiceImpl();
    }


    @Test
    public void checkProductRepository_whenNewProductAdded() {
        addNewProductToRepository("VS5");
        Assert.assertFalse(productService.loadAllProducts().isEmpty());
    }

    @Test
    public void checkProductRepository_whenAnyProductAdded() {
        Assert.assertTrue(productService.loadAllProducts().isEmpty());
    }

    @Test
    public void findCorrectProductFromRepository_byGivenProductCode() {
        addNewProductToRepository("CF");
        String givenProductCode = "CF";
        Product productFromRepository = productService.findProductByCode(givenProductCode);
        Assert.assertNotNull(productFromRepository);
        Product expectedProduct = createProduct_CF();
        Assert.assertEquals(productFromRepository, expectedProduct);
    }

    @Test
    public void canNotFindProductFromRepository_byGivenProductCode() {
        addNewProductToRepository("VS5");
        String givenInvalidProductCode = "CF11";
        Product productFromRepository = productService.findProductByCode(givenInvalidProductCode);
        Assert.assertNull(productFromRepository);
    }

    @Test
    public void addNewProductToRepository_thenCheckInRepository() {
        addNewProductToRepository("CF");
        List<Product> repositoryProducts = productService.loadAllProducts();
        Product productAddedInRepository = repositoryProducts.get(0);
        Product expectedProductBeInRepository = createProduct_CF();
        Assert.assertEquals(productAddedInRepository, expectedProductBeInRepository);
    }

    @Test
    public void sortDscProductPacks_whenUnsortedPacksGiven() {
        Product createUnsortProductPacks = createProduct_MB11();
        Product sortedProductPack=productService.sortProductPacks(createUnsortProductPacks);
        Product expectedSortedProductPacks = createSortedProductPacks_MB11();
        Assert.assertEquals(sortedProductPack,expectedSortedProductPacks);
    }

    private void addNewProductToRepository(String productCode) {
        Product addedProduct = null;
        switch (productCode) {
            case "VS5":
                addedProduct = createProduct_VS5();
                break;
            case "CF":
                addedProduct = createProduct_CF();
                break;
            case "MB11":
                addedProduct = createProduct_MB11();
                break;
        }
        productService.addProduct(addedProduct);
    }

    private Product createProduct_VS5() {
        List<Pack> packList_vs5 = new ArrayList<Pack>();
        packList_vs5.add(new Pack(3, new BigDecimal("6.99")));
        packList_vs5.add(new Pack(5, new BigDecimal("8.99")));
        Product product_vs5 = new Product("Vegemite Scroll", "VS5", packList_vs5);
        return product_vs5;
    }

    private Product createProduct_CF() {
        List<Pack> packList_cf = new ArrayList<Pack>();
        packList_cf.add(new Pack(5, new BigDecimal("9.95")));
        packList_cf.add(new Pack(9, new BigDecimal("16.99")));
        packList_cf.add(new Pack(3, new BigDecimal("5.95")));
        Product product_cf = new Product("Croissant", "CF", packList_cf);
        return product_cf;
    }

    private Product createProduct_MB11() {
        List<Pack> packList_mb11 = new ArrayList<Pack>();
        packList_mb11.add(new Pack(5, new BigDecimal("16.95")));
        packList_mb11.add(new Pack(2, new BigDecimal("9.95")));
        packList_mb11.add(new Pack(8, new BigDecimal("24.95")));
        Product product_mb11 = new Product("Blueberry Muffin", "MB11", packList_mb11);
        return product_mb11;
    }

    private Product createSortedProductPacks_MB11() {
        List<Pack> packList_mb11 = new ArrayList<Pack>();
        packList_mb11.add(new Pack(8, new BigDecimal("24.95")));
        packList_mb11.add(new Pack(5, new BigDecimal("16.95")));
        packList_mb11.add(new Pack(2, new BigDecimal("9.95")));
        Product product_mb11 = new Product("Blueberry Muffin", "MB11", packList_mb11);
        return product_mb11;
    }

}
