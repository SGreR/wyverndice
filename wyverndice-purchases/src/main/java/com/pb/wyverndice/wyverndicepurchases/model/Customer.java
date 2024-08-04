package com.pb.wyverndice.wyverndicepurchases.model;


import com.pb.wyverndice.wyverndicepurchases.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Customer implements Serializable {
    public Long id;
    private String name;
    private String email;
    private String password;
    private List<Address> addresses = new ArrayList<>();
    private Role role;
    private List<Purchase> purchases = new ArrayList<>();
    private List<Long> purchaseIds = new ArrayList<>();
}
