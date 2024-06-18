package com.pb.wyverndice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pb.wyverndice.enums.DiceStyle;
import com.pb.wyverndice.enums.NumberOfSides;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Entity
public class Die implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Enumerated(EnumType.STRING)
    private NumberOfSides sides;
    @ElementCollection
    @CollectionTable(name = "die_colors", joinColumns = @JoinColumn(name = "die_id"))
    @MapKeyColumn(name = "color_key")
    @Column(name = "color_value")
    private Map<String, String> colors = new HashMap<>();
    {
        colors.put("Main", "Black");
    }
    @Enumerated(EnumType.STRING)
    private DiceStyle style = DiceStyle.SOLID;
    private String numberColors = "White";
    @ManyToOne
    @JoinColumn(name = "diceset_id")
    @JsonBackReference
    private DiceSet diceSet = null;
    private double price;

    public Die(){
        this.sides = NumberOfSides.TWENTY;
        this.price = calculatePrice();
    }
    public Die(NumberOfSides sides){
        this.sides = sides;
        this.price = calculatePrice();
    }
    public Die(NumberOfSides sides, DiceSet diceSet){
        this.sides = sides;
        this.diceSet = diceSet;
        this.price = calculatePrice();
    }

    public double getPrice(){
        return calculatePrice();
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
