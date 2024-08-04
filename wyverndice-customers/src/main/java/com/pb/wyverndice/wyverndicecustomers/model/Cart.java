package com.pb.wyverndice.wyverndicecustomers.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    public Long id;
    private List<Product> products = new ArrayList<>();
    private double deliveryFee;
    private double finalPrice;

    public double getFinalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum() + deliveryFee;
    }

    public Purchase toOrder(){
        return new Purchase(this);
    }
}
