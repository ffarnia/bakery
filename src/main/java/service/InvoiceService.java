package service;

import domain.Invoice;
import domain.Order;

/**
 * Created by Fazel on 11/6/2019.
 */
public interface InvoiceService {

    Invoice generateInvoice(Order order);

}
