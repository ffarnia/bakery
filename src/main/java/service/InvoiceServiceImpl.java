package service;

import domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Fazel on 11/7/2019.
 */
public class InvoiceServiceImpl implements InvoiceService {

    private ProductService productService;

    public InvoiceServiceImpl(ProductService productService) {
        this.productService = productService;
    }

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


    private Invoice calculateInvoiceDetail(OrderItem orderItem) {
        int orderItemAmount = orderItem.getAmout();
        if (orderItemAmount == 0) {
            return generateInvaidOrder(MessageConstant.ORDER_AMOUNT_NOT_ENOUGH,orderItem);
        }
        Product repositoryProduct = productService.findProductByCode(orderItem.getProduct().getCode());
        if (repositoryProduct == null) {
            return generateInvaidOrder(MessageConstant.PRODUCT_CODE_UNAVAILABLE, orderItem);
        }
        repositoryProduct = productService.sortProductPacks(repositoryProduct);
        List<Pack> packList = packagingAlgorithm(repositoryProduct.getPacks(), orderItemAmount);
        if (packList == null || packList.isEmpty()) {
            return  generateInvaidOrder(MessageConstant.ORDER_AMOUNT_NOT_PACKAGING, orderItem);
        }

        Map<Pack, Long> packCountMap = packList.stream().collect(Collectors.groupingBy(Function.<Pack>identity(), Collectors.counting()));
        BigDecimal totalPrice = packList.stream().map(Pack::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Invoice invoice = new Invoice(totalPrice, orderItem, packCountMap);
        return invoice;
    }

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

    private Invoice generateInvaidOrder(String informationMessage, OrderItem orderItem) {
        Invoice invalidOrder = new Invoice(new BigDecimal("0"), orderItem, null);
        invalidOrder.setMessage(informationMessage);
        return invalidOrder;

    }
}
