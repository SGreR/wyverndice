package com.pb.wyverndice.wyverndicecustomers.model;

import com.pb.wyverndice.wyverndicecustomers.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Product implements Serializable {
    public Long id;
    private ProductType type;
    private double price = 0.0;
}
