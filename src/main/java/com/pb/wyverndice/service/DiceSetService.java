package com.pb.wyverndice.service;

import com.pb.wyverndice.enums.DiceStyle;
import com.pb.wyverndice.enums.NumberOfDice;
import com.pb.wyverndice.exception.ResourceNotFoundException;
import com.pb.wyverndice.model.DiceSet;
import com.pb.wyverndice.model.PremadeDiceSet;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiceSetService {

    List<DiceSet> diceSetList = initValues();

    private List<DiceSet> initValues() {
        List<DiceSet> diceSets = new ArrayList<>();
        diceSets.add(new PremadeDiceSet(0,"Classic Set", new HashMap<>(Map.of("Main", "Black")), "Gold", DiceStyle.SOLID, NumberOfDice.TEN));
        diceSets.add(new PremadeDiceSet(1,"Smoky Set", new HashMap<>(Map.of("Main", "Clear", "Secondary", "Blue")), "Gold", DiceStyle.SMOKE, NumberOfDice.TEN));
        diceSets.add(new PremadeDiceSet(2,"Fantasy Set", new HashMap<>(Map.of("Main", "Red", "Secondary", "Gold", "Tertiary", "Black")), "White", DiceStyle.MARBLED, NumberOfDice.EIGHT));
        diceSets.add(new PremadeDiceSet(3,"Metallic Set", new HashMap<>(Map.of("Main", "Blue", "Secondary", "Purple")), "Silver", DiceStyle.TRANSLUCENT, NumberOfDice.TEN));
        return diceSets;
    }

    public List<DiceSet> getAll(){
        return diceSetList;
    }

    public DiceSet getById(int id){
        if(id < 0){
            throw new ResourceNotFoundException("Valor Invalido");
        }else {
            Optional<DiceSet> diceSetOptional = diceSetList.stream().filter(diceSet -> diceSet.getId() == id).findFirst();
            if(diceSetOptional.isEmpty()) throw new ResourceNotFoundException("Objeto Nao Encontrado");
            return diceSetOptional.get();
        }
    }

    public void save(DiceSet diceSet){
        diceSetList.add(diceSet);
    }

    public List<DiceSet> filterByName(String name){
        List<DiceSet> all = getAll();
        return all.stream().filter(diceSet -> diceSet.getName().startsWith(name)).toList();

    }

    public void deleteById(int id){
        if(resourceNotFound(id)){
            throw new ResourceNotFoundException("Conjunto Não Localizado");
        }
        diceSetList.remove(diceSetList.get(id));
    }

    public void update(Integer id, DiceSet diceSet) {
        if(resourceNotFound(id)){
            throw new ResourceNotFoundException("Conjunto Não Localizado");
        }
        diceSetList.set(id,diceSet);
    }

    private boolean resourceNotFound(int id) {
        return diceSetList.stream().filter(diceSet -> diceSet.getId() == id).findFirst().isEmpty();
    }

}
