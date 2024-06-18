package com.pb.wyverndice.service;

import com.pb.wyverndice.model.DiceSet;
import com.pb.wyverndice.model.PremadeDiceSet;
import com.pb.wyverndice.repository.DiceSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiceSetService {

    @Autowired
    private DiceSetRepository diceSetRepository;

    public List<DiceSet> getAllDiceSets(){
        return diceSetRepository.findAll();
    }

    public DiceSet getDiceSetById(Long id){
        return diceSetRepository.findById(id).orElse(null);
    }

    public DiceSet createDiceSet(DiceSet diceSet){
        return diceSetRepository.save(diceSet);
    }

    public List<DiceSet> filterDiceSetByName(String name){
        return diceSetRepository.findAll()
                .stream()
                .filter(diceSet ->
                        diceSet.getName()
                        .startsWith(name)).toList();

    }

    public void deleteDiceSetById(Long id){
        diceSetRepository.deleteById(id);
    }

    public void updateDiceSet(Long id, DiceSet newDiceSet) {
        PremadeDiceSet newSet = (PremadeDiceSet) newDiceSet;
        PremadeDiceSet diceSet = (PremadeDiceSet) diceSetRepository.findById(id).orElse(null);
        if (diceSet != null) {
            diceSet.setName(newSet.getName());
            diceSet.setColors(newSet.getColors());
            diceSet.setNumberColors(newSet.getNumberColors());
            diceSet.setStyle(newSet.getStyle());
            diceSet.setNumberOfDice(newSet.getNumberOfDice());
            diceSetRepository.save(diceSet);
        }
    }

}
