import domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.InvoiceService;
import service.InvoiceServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fazel on 11/8/2019.
 */
public class InvoiceServiceTest {

    private List<Product> repositoryProducts;
    private Order preparedOrder;
    private InvoiceService invoiceService;

    @Before
    public void initRepository() {
        ProductService productService = prepareProducts();
        this.repositoryProducts = productService.loadAllProducts();
        this.invoiceService = new InvoiceServiceImpl(productService);
        this.preparedOrder = prepareOrder();
    }

    @Test
    public void order_whenNotExistsInProductRepository() {
        System.out.println("InvoiceServiceTest.order_whenNotExistsInProductRepository");
        List<Invoice> invalidInvoices = invoiceService.generateInvoice(createInvalidOrder());
        Invoice invoice = invalidInvoices.get(0);
        Assert.assertEquals(invoice.getMessage(), MessageConstant.PRODUCT_CODE_UNAVAILABLE);
        Assert.assertNull(invoice.getPackagingMap());
        Assert.assertEquals(invoice.getTotalPriceItem(), new BigDecimal("0"));
    }

    @Test
    public void order_whenAtLeastMinimumAmountOfProductPacksNeeded() {
        System.out.println("InvoiceServiceTest.order_whenAtLeastMinimumAmountOfProductPacksNeeded");
        List<Invoice> invalidInvoices = invoiceService.generateInvoice(createOrderWithNotEnoughToPackaging());
        Invoice invoice = invalidInvoices.get(0);
        Assert.assertEquals(invoice.getMessage(), MessageConstant.ORDER_AMOUNT_NOT_PACKAGING);
        Assert.assertNull(invoice.getPackagingMap());
        Assert.assertEquals(invoice.getTotalPriceItem(), new BigDecimal("0"));
    }

    @Test
    public void order_whenRequestedAmountCanNotFitPacks() {
        System.out.println("InvoiceServiceTest.order_whenRequestedAmountCanNotFitPacks");
        List<Invoice> invalidInvoices = invoiceService.generateInvoice(createOrderWithoutPackageable());
        Invoice invoice = invalidInvoices.get(0);
        Assert.assertEquals(invoice.getMessage(), MessageConstant.ORDER_AMOUNT_NOT_PACKAGING);
        Assert.assertNull(invoice.getPackagingMap());
        Assert.assertEquals(invoice.getTotalPriceItem(), new BigDecimal("0"));
    }

    @Test
    public void Order_whenAllOrdersAreCorrect() {
        System.out.println("InvoiceServiceTest.Order_whenAllOrdersAreCorrect");
        List<Invoice> createdInvoiceByService = invoiceService.generateInvoice(preparedOrder);
        List<Invoice> expectedInvoice = createExpectedInvoice();
        Assert.assertEquals(createdInvoiceByService.get(0).getTotalPriceItem(), expectedInvoice.get(0).getTotalPriceItem());
        Assert.assertEquals(createdInvoiceByService.get(1).getTotalPriceItem(), expectedInvoice.get(1).getTotalPriceItem());
        Assert.assertEquals(createdInvoiceByService.get(2).getTotalPriceItem(), expectedInvoice.get(2).getTotalPriceItem());
        Assert.assertNull(createdInvoiceByService.get(0).getMessage());
        Assert.assertNull(createdInvoiceByService.get(1).getMessage());
        Assert.assertNull(createdInvoiceByService.get(2).getMessage());
        Assert.assertEquals(createdInvoiceByService.get(0).getPackagingMap().get(new Pack(5, new BigDecimal("8.99"))).intValue(), 2);
        Assert.assertEquals(createdInvoiceByService.get(1).getPackagingMap().get(new Pack(8, new BigDecimal("24.95"))).intValue(), 1);
        Assert.assertEquals(createdInvoiceByService.get(1).getPackagingMap().get(new Pack(2, new BigDecimal("9.95"))).intValue(), 3);
        Assert.assertEquals(createdInvoiceByService.get(2).getPackagingMap().get(new Pack(5, new BigDecimal("9.95"))).intValue(), 2);
        Assert.assertEquals(createdInvoiceByService.get(2).getPackagingMap().get(new Pack(3, new BigDecimal("5.95"))).intValue(), 1);
    }

    private List<Invoice> createExpectedInvoice() {
        List<Invoice> expectedInvoiceList = new ArrayList<>();
        OrderItem orderItem_vs5 = new OrderItem(10, new Product("VS5"));
        Map<Pack, Long> packLongMap_vs5 = new HashMap<>();
        packLongMap_vs5.put(new Pack(5, new BigDecimal("8.99")), 2L);
        Invoice invoice_vs5 = new Invoice(new BigDecimal("17.98"), orderItem_vs5, packLongMap_vs5);
        OrderItem orderItem_mb11 = new OrderItem(14, new Product("MB11"));
        Map<Pack, Long> packLongMap_mb11 = new HashMap<>();
        packLongMap_mb11.put(new Pack(8, new BigDecimal("24.95")), 1L);
        packLongMap_mb11.put(new Pack(2, new BigDecimal("9.95")), 3L);
        Invoice invoice_mb11 = new Invoice(new BigDecimal("54.80"), orderItem_mb11, packLongMap_mb11);
        OrderItem orderItem_cf = new OrderItem(13, new Product("CF"));
        Map<Pack, Long> packLongMap_cf = new HashMap<>();
        packLongMap_cf.put(new Pack(5, new BigDecimal("9.95")), 2L);
        packLongMap_cf.put(new Pack(3, new BigDecimal("5.95")), 1L);
        Invoice invoice_cf = new Invoice(new BigDecimal("25.85"), orderItem_cf, packLongMap_cf);

        expectedInvoiceList.add(invoice_vs5);
        expectedInvoiceList.add(invoice_mb11);
        expectedInvoiceList.add(invoice_cf);

        return expectedInvoiceList;
    }

    private Order createInvalidOrder() {
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItemInvalid = new OrderItem(10, new Product("Invalid_Product_Code"));
        orderItems.add(orderItemInvalid);
        return new Order(orderItems);
    }

    private Order createOrderWithNotEnoughToPackaging() {
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem_cf_lowerMinNumOfPack = new OrderItem(2, new Product("CF"));
        orderItems.add(orderItem_cf_lowerMinNumOfPack);
        return new Order(orderItems);
    }

    private Order createOrderWithoutPackageable() {
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem_cf_lowerMinNumOfPack = new OrderItem(4, new Product("VS5"));
        orderItems.add(orderItem_cf_lowerMinNumOfPack);
        return new Order(orderItems);
    }

    private Order prepareOrder() {
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem_VS5 = new OrderItem(10, new Product("VS5"));
        OrderItem orderItem_MB11 = new OrderItem(14, new Product("MB11"));
        OrderItem orderItem_CF = new OrderItem(13, new Product("CF"));
        orderItems.add(orderItem_VS5);
        orderItems.add(orderItem_MB11);
        orderItems.add(orderItem_CF);
        return new Order(orderItems);
    }

    private ProductService prepareProducts() {
        ProductService productService = new ProductServiceImpl();
        List<Pack> packList_vs5 = new ArrayList<Pack>();
        packList_vs5.add(new Pack(3, new BigDecimal("6.99")));
        packList_vs5.add(new Pack(5, new BigDecimal("8.99")));
        Product product_vs5 = new Product("Vegemite Scroll", "VS5", packList_vs5);

        List<Pack> packList_mb11 = new ArrayList<Pack>();
        packList_mb11.add(new Pack(2, new BigDecimal("9.95")));
        packList_mb11.add(new Pack(5, new BigDecimal("16.95")));
        packList_mb11.add(new Pack(8, new BigDecimal("24.95")));
        Product product_mb11 = new Product("Blueberry Muffin", "MB11", packList_mb11);

        List<Pack> packList_cf = new ArrayList<Pack>();
        packList_cf.add(new Pack(5, new BigDecimal("9.95")));
        packList_cf.add(new Pack(9, new BigDecimal("16.99")));
        packList_cf.add(new Pack(3, new BigDecimal("5.95")));
        Product product_cf = new Product("Croissant", "CF", packList_cf);

        productService.addProduct(product_cf);
        productService.addProduct(product_mb11);
        productService.addProduct(product_vs5);

        return productService;
    }
}
