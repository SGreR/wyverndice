package com.pb.wyverndice.wyverndicedie.model;


import com.pb.wyverndice.wyverndicedie.enums.Colors;
import com.pb.wyverndice.wyverndicedie.enums.DiceStyle;
import com.pb.wyverndice.wyverndicedie.enums.NumberOfDice;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PremadeDiceSet extends DiceSet {

    private String name;
    @ElementCollection
    private List<Colors> colors = new ArrayList<>(listOf(Colors.BLACK));
    private Colors numberColor = Colors.WHITE;
    private DiceStyle style = DiceStyle.SOLID;
    private NumberOfDice numberOfDice = NumberOfDice.EIGHT;

    @PostConstruct
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

    @Override
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
}
