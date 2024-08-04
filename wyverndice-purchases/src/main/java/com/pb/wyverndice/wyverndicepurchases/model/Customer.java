package com.pb.wyverndice.wyverndicepurchases.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.pb.wyverndice.wyverndicepurchases.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Purchase> purchases = new ArrayList<>();

    public Customer(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Customer() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.role = Role.Client;
    }
}
