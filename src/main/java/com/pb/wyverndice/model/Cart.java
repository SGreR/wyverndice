package com.pb.wyverndice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart<T extends DiceSet> {
    public Long id;
    private List<T> diceSets;
    private double deliveryFee;
    private double finalPrice;

    public Cart() {
        this.diceSets = new ArrayList<>();
    }

    public Cart(List<T> products) {
        this.diceSets = products;
    }

    public double getFinalPrice() {
        return diceSets.stream().mapToDouble(diceSets -> diceSets.getPrice()).sum() + deliveryFee;
    }

    public Purchase toOrder(){
        return new Purchase(this);
    }
}
