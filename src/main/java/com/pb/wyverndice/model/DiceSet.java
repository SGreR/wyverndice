package com.pb.wyverndice.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PremadeDiceSet.class, name = "premade"),
})
public abstract class DiceSet {
    int id;
    String name;
    List<Die> dice;

    double price;

    public DiceSet(){
        this.id = 0;
        this.name = "DiceSet";
        this.dice = new ArrayList<>();
    }

    public DiceSet(int id, String name) {
        this.id = id;
        this.name = name;
        this.dice = new ArrayList<>();
    }

    public DiceSet(int id, String name, List<Die> dice) {
        this.id = id;
        this.name = name;
        this.dice = dice;
    }

    protected abstract double calculatePrice();
}
