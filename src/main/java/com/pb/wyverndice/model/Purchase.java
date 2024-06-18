package com.pb.wyverndice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    @ManyToMany
    @JoinTable(
            name = "purchase_dicesets",
            joinColumns = @JoinColumn(name = "purchases_id"),
            inverseJoinColumns = @JoinColumn(name = "dicesets_id")
    )
    private List<DiceSet> diceSets;
    private double itemPrice;
    private double deliveryFee;
    private double finalPrice;

    public Purchase(){
        this.customer = null;
        this.diceSets = new ArrayList<>();
        this.itemPrice = getItemPrice();
        this.finalPrice = getFinalPrice();
    }

    public Purchase(Cart cart){
        this.diceSets = cart.getDiceSets().stream().toList();
        this.itemPrice = getItemPrice();
        this.finalPrice = getFinalPrice();
    }

    public Purchase(Cart cart, Customer customer){
        this.customer = customer;
        this.diceSets = cart.getDiceSets().stream().toList();
        this.itemPrice = getItemPrice();
        this.finalPrice = getFinalPrice();
    }

    public double getItemPrice(){
        return this.diceSets.stream().mapToDouble(DiceSet::getPrice).sum();
    }

    public double getFinalPrice() {
        return getItemPrice() + getDeliveryFee();
    }
}
