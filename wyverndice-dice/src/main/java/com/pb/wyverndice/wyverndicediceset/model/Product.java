package com.pb.wyverndice.wyverndicediceset.model;

import com.pb.wyverndice.wyverndicediceset.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private double price = 0.0;
}
