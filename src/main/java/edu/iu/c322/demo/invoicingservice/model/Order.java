package edu.iu.c322.demo.invoicingservice.model;

import java.util.List;

public record Order(int id,
                    int customerId,
                    float total,
                    Address shippingAddress,
                    List<OrderItem> items,
                    Payment payment) {
}
