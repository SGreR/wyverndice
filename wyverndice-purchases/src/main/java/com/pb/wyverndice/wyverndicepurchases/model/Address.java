package com.pb.wyverndice.wyverndicepurchases.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Address {
    private Long Id;
    private String number;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private Customer customer;
}
