package com.pb.wyverndice.wyverndicepurchases.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Transient
    private Customer customer = null;
    private Long customerId = null;
    @ElementCollection
    private Set<Long> productIds = new HashSet<>();
    @Transient
    private List<Product> products = new ArrayList<>();
    private double purchasePrice = 0.0;
    private double deliveryFee = 0.0;
    private double finalPrice = 0.0;

    public Purchase(Cart cart) {
        this.products = cart.getProducts();
    }

    public double getPurchasePrice(){
        return this.products.stream().mapToDouble(Product::getPrice).sum();
    }

    public double getFinalPrice() {
        return getPurchasePrice() + getDeliveryFee();
    }
}
