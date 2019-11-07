package service;

import domain.*;

import java.util.ArrayList;
import java.util.List;
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
    public Invoice generateInvoice(Order order) {

        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            calculateInvoice(orderItem);
        }
        return null;
    }

    private void calculateInvoice(OrderItem orderItem) {
        int orderItemAmount = orderItem.getAmout();
        Product repositoryProduct = productService.findProductByCode(orderItem.getProduct().getCode());
        repositoryProduct = productService.sortProductPacks(repositoryProduct);
        List<Pack> packList = packagingAlgorithm(repositoryProduct.getPacks(), orderItemAmount);
        System.out.println(packList);
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
                final int finalRemainItemAmount = remainItemAmount%numOfPacks;
                if (productPacks.stream().filter(pack -> finalRemainItemAmount%pack.getQuantity()==0).findFirst().isPresent()) {
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
}
