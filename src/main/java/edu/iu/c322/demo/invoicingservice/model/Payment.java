package edu.iu.c322.demo.invoicingservice.model;

public record Payment(int id,
                      String method,
                      String number,
                      Address billingAddress) {
}
