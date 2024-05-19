package com.pb.wyverndice.model;

import com.pb.wyverndice.enums.DiceStyle;
import com.pb.wyverndice.enums.NumberOfDice;
import com.pb.wyverndice.enums.NumberOfSides;
import jakarta.annotation.PostConstruct;
import lombok.Data;


import java.util.*;

@Data
public class PremadeDiceSet extends DiceSet {

    private Map<String, String> colors = new HashMap<>();
    {
        colors.put("Main", "Black");
    }
    private String numberColors = "White";
    private DiceStyle style = DiceStyle.SOLID;

    private NumberOfDice numberOfDice;

    public PremadeDiceSet(NumberOfDice numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet(this.numberOfDice.getValue());
    }

    public PremadeDiceSet(){
        this.numberOfDice = NumberOfDice.EIGHT;
        this.colors = new HashMap<>();
        {
            colors.put("Main", "Black");
        }
        this.numberColors = "White";
        this.style = DiceStyle.SOLID;
        this.dice = new ArrayList<>();
        createSet(this.numberOfDice.getValue());
    }

    public PremadeDiceSet(Map<String, String> colors, String numberColors, DiceStyle style, NumberOfDice numberOfDice) {
        this.colors = colors;
        this.numberColors = numberColors;
        this.style = style;
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet(this.numberOfDice.getValue());
    }

    public PremadeDiceSet(int id, String name, Map<String, String> colors, String numberColors, DiceStyle style, NumberOfDice numberOfDice) {
        super(id, name);
        this.colors = colors;
        this.numberColors = numberColors;
        this.style = style;
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet(this.numberOfDice.getValue());
    }

    public PremadeDiceSet(int id, String name, List<Die> dice, Map<String, String> colors, String numberColors, DiceStyle style, NumberOfDice numberOfDice) {
        super(id, name, dice);
        this.colors = colors;
        this.numberColors = numberColors;
        this.style = style;
        this.numberOfDice = numberOfDice;
        this.dice = new ArrayList<>();
        createSet(this.numberOfDice.getValue());
    }

    private void createSet(int numberOfDiceValue){
        if(numberOfDiceValue != dice.size()){
            this.dice.clear();
            if (numberOfDiceValue == 10) {
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
            setDiceColors();
        }
    }

    @PostConstruct
    private void setDiceColors(){
        for (Die die : this.dice){
            die.setNumberColors(numberColors);
            die.setColors(colors);
            die.setStyle(style);
        }
    }

    public double getPrice() {
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

    public void setNumberOfDice(NumberOfDice numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.createSet(this.numberOfDice.getValue());
    }
}
