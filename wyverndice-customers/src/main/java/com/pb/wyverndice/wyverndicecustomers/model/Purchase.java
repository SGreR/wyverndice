package com.pb.wyverndice.wyverndicecustomers.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class Purchase implements Serializable {
    public Long id;
    private Customer customer = null;
    private Long customerId = null;
    private Set<Long> productIds = new HashSet<>();
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
