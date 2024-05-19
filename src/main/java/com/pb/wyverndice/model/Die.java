package com.pb.wyverndice.model;

import com.pb.wyverndice.enums.DiceStyle;
import com.pb.wyverndice.enums.NumberOfSides;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Die {
    private NumberOfSides sides;
    private Map<String, String> colors = new HashMap<>();
    {
        colors.put("Main", "Black");
    }
    private DiceStyle style = DiceStyle.SOLID;
    private String numberColors = "White";
    private double price;

    public Die(NumberOfSides sides){
        this.sides = sides;
        this.price = calculatePrice();
    }

    public double getPrice(){
        return calculatePrice();
    }

    private double calculatePrice(){
        double price = 0;
        double basePrice = sides.getSides() * 1.75;
        price += basePrice;
        price *= style.getMultiplier();
        price += (price * ((colors.size() - 1) * 0.05));
        return Math.round(price * 100) / 100.0;
    }

}
