package com.pb.wyverndice.wyverndicepurchases.model;

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
        return calculatePurchasePrice();
    }

    public void setPurchasePrice(){
        this.purchasePrice = calculatePurchasePrice();
    }

    public double getDeliveryFee(){
        return calculateDeliveryFee();
    }

    public void setDeliveryFee(){
        this.deliveryFee = calculateDeliveryFee();
    }

    public double getFinalPrice() {
        return calculateFinalPrice();
    }

    public void setFinalPrice() {
        this.finalPrice = calculateFinalPrice();
    }

    @PrePersist
    @PreUpdate
    private void setPrices(){
        setPurchasePrice();
        setDeliveryFee();
        setFinalPrice();
    }

    public double calculatePurchasePrice(){
        return this.products.stream().mapToDouble(Product::getPrice).sum();
    }

    public double calculateFinalPrice(){
        return getPurchasePrice() + getDeliveryFee();
    }

    public double calculateDeliveryFee(){
        return 0.0;
    }
}
