package edu.iu.c322.demo.invoicingservice.model;

public record Address(int id,
                      String state,
                      String city,
                      int postalCode) {
}
