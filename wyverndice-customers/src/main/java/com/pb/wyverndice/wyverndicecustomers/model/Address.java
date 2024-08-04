package com.pb.wyverndice.wyverndicecustomers.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String number;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;
}
