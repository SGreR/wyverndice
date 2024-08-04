package com.pb.wyverndice.wyverndicediceset.model;

import com.pb.wyverndice.wyverndicediceset.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Enumerated(EnumType.STRING)
    protected ProductType type;
    protected double price;

    public double getPrice(){
        return calculatePrice();
    }

    protected double calculatePrice() {
        return 0.0;
    }
}
