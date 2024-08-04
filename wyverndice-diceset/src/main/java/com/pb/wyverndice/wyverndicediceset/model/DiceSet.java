package com.pb.wyverndice.wyverndicediceset.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DiceSet extends Product {

    String type = "";
    String name = "";
    List<Die> dice = new ArrayList<>();
    Set<Long> diceIds = new HashSet<>();

    @PostConstruct
    protected abstract double calculatePrice();
}
