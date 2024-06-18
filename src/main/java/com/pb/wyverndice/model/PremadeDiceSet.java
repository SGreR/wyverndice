package com.pb.wyverndice.model;

import com.pb.wyverndice.enums.DiceStyle;
import com.pb.wyverndice.enums.NumberOfDice;
import com.pb.wyverndice.enums.NumberOfSides;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;


import java.util.*;

@Data
@Entity
public class PremadeDiceSet extends DiceSet {

    @ElementCollection
    @CollectionTable(name = "premade_dice_set_colors", joinColumns = @JoinColumn(name = "premade_dice_set_id"))
    @MapKeyColumn(name = "color_key")
    @Column(name = "color_value")
    private Map<String, String> colors = new HashMap<>();
    {
        colors.put("Main", "Black");
    }
    private String numberColors = "White";
    private DiceStyle style = DiceStyle.SOLID;
    private NumberOfDice numberOfDice;

    public PremadeDiceSet(){
        this.numberOfDice = NumberOfDice.EIGHT;
        this.dice = new ArrayList<>();
        this.setPrice(this.calculatePrice());
    }

    public PremadeDiceSet(NumberOfDice numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet();
        this.setPrice(this.calculatePrice());
    }

    public PremadeDiceSet(Map<String, String> colors, String numberColors, DiceStyle style, NumberOfDice numberOfDice) {
        this.colors = colors;
        this.numberColors = numberColors;
        this.style = style;
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet();
        this.setPrice(this.calculatePrice());
    }

    public PremadeDiceSet(String name, Map<String, String> colors, String numberColors, DiceStyle style, NumberOfDice numberOfDice) {
        super(name);
        this.colors = colors;
        this.numberColors = numberColors;
        this.style = style;
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet();
        this.setPrice(this.calculatePrice());
    }

    public PremadeDiceSet(String name, List<Die> dice, Map<String, String> colors, String numberColors, DiceStyle style, NumberOfDice numberOfDice) {
        super(name, dice);
        this.colors = colors;
        this.numberColors = numberColors;
        this.style = style;
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet();
        this.setPrice(this.calculatePrice());
    }

    public void createSet(){
        if (this.numberOfDice.getValue() == 10) {
            this.dice.add(new Die(NumberOfSides.TWO));
            this.dice.add(new Die(NumberOfSides.TWENTY));
        }
        this.dice.add(new Die(NumberOfSides.TWO));
        this.dice.add(new Die(NumberOfSides.FOUR));
        this.dice.add(new Die(NumberOfSides.SIX));
        this.dice.add(new Die(NumberOfSides.EIGHT));
        this.dice.add(new Die(NumberOfSides.TEN));
        this.dice.add(new Die(NumberOfSides.PERCENTILE));
        this.dice.add(new Die(NumberOfSides.TWELVE));
        this.dice.add(new Die(NumberOfSides.TWENTY));
        this.dice.sort(Comparator.comparing(d -> d.getSides().ordinal()));
        for (Die dice : this.dice){
            dice.setDiceSet(this);
        }
        setDiceColors();
    }

    @PostConstruct
    private void setDiceColors(){
        for (Die die : this.dice){
            die.setNumberColors(numberColors);
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

    public void setColors(Map<String, String> colors) {
        this.colors = colors;
        this.setDiceColors();
    }

    public void setStyle(DiceStyle style) {
        this.style = style;
        this.setDiceColors();
    }

    public void setNumberColors(String numberColors) {
        this.numberColors = numberColors;
        this.setDiceColors();
    }
}
