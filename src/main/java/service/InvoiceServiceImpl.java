package service;

import domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Created by Fazel on 11/7/2019.
 * <p>This Service class generate invoice and calculate final invoice base on given order</p>
 *
 */
public class InvoiceServiceImpl implements InvoiceService {

    private ProductService productService;

    public InvoiceServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    /**
     * <p>Generate invoice for given order</p>
     * @param order
     * @return List of Invoices List<Invoice>
     */
    @Override
    public List<Invoice> generateInvoice(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        List<Invoice> invoices = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            invoices.add(calculateInvoiceDetail(orderItem));
            System.out.println(calculateInvoiceDetail(orderItem));
        }
        return invoices;
    }

    /**
     * <p>Calculate details of invoice for each orderItem of an order</p>
     * @param orderItem
     * @return invoice
     */
    private Invoice calculateInvoiceDetail(OrderItem orderItem) {
        int orderItemAmount = orderItem.getAmount();
        if (orderItemAmount == 0) {
            return generateInvalidOrder(MessageConstant.ORDER_AMOUNT_NOT_ENOUGH, orderItem);
        }
        Product repositoryProduct = productService.findProductByCode(orderItem.getProduct().getCode());
        if (repositoryProduct == null) {
            return generateInvalidOrder(MessageConstant.PRODUCT_CODE_UNAVAILABLE, orderItem);
        }
        repositoryProduct = productService.sortProductPacks(repositoryProduct);
        List<Pack> packList = packagingAlgorithm(repositoryProduct.getPacks(), orderItemAmount);
        if (packList == null || packList.isEmpty()) {
            return  generateInvalidOrder(MessageConstant.ORDER_AMOUNT_NOT_PACKAGING, orderItem);
        }
        Map<Pack, Long> packCountMap = packList.stream().collect(Collectors.groupingBy(Function.<Pack>identity(), Collectors.counting()));
        BigDecimal totalPrice = packList.stream().map(Pack::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Invoice invoice = new Invoice(totalPrice, orderItem, packCountMap);
        return invoice;
    }

    /**
     *<p> Packaging every order amount into available packs of product in repository and should process the minimum number of packs</p>
     * @param productPacks packs of every product
     * @param orderItemAmount
     * @return List<Pack>
     */
    private List<Pack> packagingAlgorithm(List<Pack> productPacks, int orderItemAmount) {
        int remainItemAmount = orderItemAmount;
        if (productPacks.size() == 0) {
            return null;
        }
        List<Pack> packagedList = new ArrayList<>();
        for (Pack productPack : productPacks) {
            int numOfPacks = productPack.getQuantity();
            if (remainItemAmount < numOfPacks) {
                continue;
            } else {
                final int finalRemainItemAmount = remainItemAmount % numOfPacks;
                if (productPacks.stream().filter(pack -> finalRemainItemAmount % pack.getQuantity() == 0).findFirst().isPresent()) {
                    for (int i = 0; i < (remainItemAmount / numOfPacks); i++) {
                        packagedList.add(productPack);
                    }
                } else {
                    continue;
                }
                remainItemAmount = remainItemAmount % numOfPacks;
            }
        }
        if (packagedList.stream().collect(Collectors.summingInt(Pack::getQuantity)) == orderItemAmount) {
            return packagedList;
        } else {
            return packagingAlgorithm(productPacks.subList(1, productPacks.size()), orderItemAmount);
        }
    }

    private Invoice generateInvalidOrder(String informationMessage, OrderItem orderItem) {
        Invoice invalidOrder = new Invoice(new BigDecimal("0"), orderItem, null);
        invalidOrder.setMessage(informationMessage);
        return invalidOrder;

    }
}
