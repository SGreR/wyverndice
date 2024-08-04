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

    public Die(NumberOfSides sides){
        this.sides = sides;
    }


    @PreUpdate
    @PrePersist
    private void setInfo(){
        setPrice();
        setType();
    }

    private void setPrice(){
        this.price = calculatePrice();
    }

    private void setType(){
        this.type = ProductType.DIE;
    }

    @Override
    protected double calculatePrice(){
        double price = 0;
        double basePrice = sides.getSides() * 1.75;
        price += basePrice;
        price *= style.getMultiplier();
        price += (price * ((colors.size() - 1) * 0.05));
        return Math.round(price * 100) / 100.0;
    }

}
