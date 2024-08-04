package com.pb.wyverndice.wyverndicediceset.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pb.wyverndice.wyverndicediceset.enums.Colors;
import com.pb.wyverndice.wyverndicediceset.enums.DiceStyle;
import com.pb.wyverndice.wyverndicediceset.enums.NumberOfSides;
import com.pb.wyverndice.wyverndicediceset.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Die extends Product implements Serializable {

    private ProductType type = ProductType.DIE;
    @Enumerated(EnumType.STRING)
    private NumberOfSides sides = NumberOfSides.TWENTY;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Colors> colors = new ArrayList<>(listOf(Colors.BLACK));
    @Enumerated(EnumType.STRING)
    private DiceStyle style = DiceStyle.SOLID;
    private Colors numberColor = Colors.WHITE;
    @ManyToOne
    @JoinColumn(name = "diceset_id")
    @JsonBackReference
    private DiceSet diceSet = null;
    private double price;

    public Die(NumberOfSides sides){
        this.sides = sides;
    }

    public double getPrice(){
        return calculatePrice();
    }

    @PreUpdate
    @PrePersist
    public void setPrice(){
        this.price = calculatePrice();
    }
    protected double calculatePrice(){
        double price = 0;
        double basePrice = sides.getSides() * 1.75;
        price += basePrice;
        price *= style.getMultiplier();
        price += (price * ((colors.size() - 1) * 0.05));
        return Math.round(price * 100) / 100.0;
    }

}
