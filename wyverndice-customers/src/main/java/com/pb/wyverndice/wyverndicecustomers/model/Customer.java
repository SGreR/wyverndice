package com.pb.wyverndice.wyverndicecustomers.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pb.wyverndice.wyverndicecustomers.enums.Role;
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
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;
    @Transient
    private List<Purchase> purchases = new ArrayList<>();
}
