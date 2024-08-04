package com.pb.wyverndice.wyverndicecustomers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    public Long id;
    private double price;
}
