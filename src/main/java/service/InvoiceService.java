package service;

import domain.Invoice;
import domain.Order;

import java.util.List;

/**
 * Created by Fazel on 11/6/2019.
 */
public interface InvoiceService {

    List<Invoice> generateInvoice(Order order);

}
