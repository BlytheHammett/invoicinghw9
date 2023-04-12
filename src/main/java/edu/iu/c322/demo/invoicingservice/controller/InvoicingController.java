package edu.iu.c322.demo.invoicingservice.controller;

import edu.iu.c322.demo.invoicingservice.model.Invoice;
import edu.iu.c322.demo.invoicingservice.model.InvoiceItem;
import edu.iu.c322.demo.invoicingservice.model.Order;
import edu.iu.c322.demo.invoicingservice.model.OrderItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoicingController {

    private final WebClient orderService;

    public InvoicingController(WebClient.Builder webClientBuilder) {
        orderService = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    @GetMapping("/{orderId}")
    public Invoice findByOrderId(@PathVariable int orderId){
        Order order =  orderService.get().uri("/orders/order/{orderId}", orderId)
                .retrieve()
                .bodyToMono(Order.class).block();
        Invoice invoice = new Invoice();
        invoice.setTotal(order.total());
        invoice.setPayment(order.payment());
        // add the rest of the data items
        invoice.setOrderPlaced(new Date("3/4/2023"));
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        InvoiceItem tempItem = new InvoiceItem();
        tempItem.setStatus("shipping now");
        tempItem.setItems(order.items());
        tempItem.setOn(new Date("3/5/2023"));
        tempItem.setAddress(order.shippingAddress());
        invoiceItems.add(tempItem);
        invoice.setInvoiceItems(invoiceItems);
        return invoice;
    }


}
