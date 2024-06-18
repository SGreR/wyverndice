package com.pb.wyverndice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DiceSet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private double price;
    String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diceset_id")
    @JsonManagedReference
    List<Die> dice;

    public DiceSet(){
        this.name = "DiceSet";
        this.dice = new ArrayList<>();
    }

    public DiceSet(String name) {
        this.name = name;
        this.dice = new ArrayList<>();
    }

    public DiceSet(String name, List<Die> dice) {
        this.name = name;
        this.dice = dice;
    }

    @PostConstruct
    protected abstract double calculatePrice();
}
