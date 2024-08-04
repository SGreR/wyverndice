package com.pb.wyverndice.wyverndicediceset.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pb.wyverndice.wyverndicediceset.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DiceSet extends Product {

    String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diceset_id")
    @JsonManagedReference
    List<Die> dice = new ArrayList<>();
    private ProductType type = ProductType.PREMADE;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Colors> colors = new ArrayList<>(listOf(Colors.BLACK));
    @Enumerated(EnumType.STRING)
    private Colors numberColor = Colors.WHITE;
    @Enumerated(EnumType.STRING)
    private DiceStyle style = DiceStyle.SOLID;
    @Enumerated(EnumType.STRING)
    private NumberOfDice numberOfDice = NumberOfDice.EIGHT;
    private double price;

    public void populateDice(){
        if(type == ProductType.PREMADE && dice.isEmpty()){
            dice = listOf(
                    new Die(NumberOfSides.TWENTY),
                    new Die(NumberOfSides.TWELVE),
                    new Die(NumberOfSides.PERCENTILE),
                    new Die(NumberOfSides.TEN),
                    new Die(NumberOfSides.EIGHT),
                    new Die(NumberOfSides.SIX),
                    new Die(NumberOfSides.FOUR),
                    new Die(NumberOfSides.TWO)
            );
            if(numberOfDice == NumberOfDice.TEN){
                dice.addFirst(new Die(NumberOfSides.TWENTY));
                dice.addLast(new Die(NumberOfSides.TWO));
            }
            setDiceInfo();
            setDiceColors();
        }
    }

    private void setDiceInfo(){
        for (Die die : dice) {
            die.setDiceSet(this);
        }
    }

    private void setDiceColors(){
        for (Die die : dice){
            die.setNumberColor(numberColor);
            die.setColors(colors);
            die.setStyle(style);
        }
    }

    public double getPrice(){
        return calculatePrice();
    }

    @PrePersist
    @PreUpdate
    public void setPrice(){
        this.price = calculatePrice();
    }

    protected double calculatePrice() {
        if(dice.isEmpty()){
            return 0;
        } else {
            double price = 0;
            for (Die die : dice) {
                price += die.getPrice();
            }
            if (numberOfDice == NumberOfDice.TEN) {
                price -= price * 0.15;
            } else {
                price -= price * 0.1;
            }
            return Math.round(price / 5) * 5;
        }
    }

    public void setColors(List<Colors> colors) {
        this.colors = colors;
        this.setDiceColors();
    }

    public void setStyle(DiceStyle style) {
        this.style = style;
        this.setDiceColors();
    }

    public void setNumberColors(Colors numberColor) {
        this.numberColor = numberColor;
        this.setDiceColors();
    }

    public void setId(Long id){
        this.id = id;
        this.setDiceInfo();
    }
}
