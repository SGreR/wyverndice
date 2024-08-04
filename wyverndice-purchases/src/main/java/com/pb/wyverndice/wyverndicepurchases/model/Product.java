package com.pb.wyverndice.wyverndicepurchases.model;

import com.pb.wyverndice.wyverndicepurchases.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    public Long id;
    private ProductType type;
    private double price = 0.0;
}
