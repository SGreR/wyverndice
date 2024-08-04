package com.pb.wyverndice.wyverndicedie.model;

import com.pb.wyverndice.wyverndicedie.enums.Colors;
import com.pb.wyverndice.wyverndicedie.enums.DiceStyle;
import com.pb.wyverndice.wyverndicedie.enums.NumberOfSides;
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
public class Die implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Enumerated(EnumType.STRING)
    private NumberOfSides sides = NumberOfSides.TWENTY;
    @ElementCollection
    private List<Colors> colors = new ArrayList<>(listOf(Colors.BLACK));
    @Enumerated(EnumType.STRING)
    private DiceStyle style = DiceStyle.SOLID;
    private Colors numberColor = Colors.WHITE;
    private DiceSet diceSet = null;
    private Long diceSetId;
    private double price;

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
