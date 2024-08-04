package com.pb.wyverndice.wyverndicediceset.service;

import com.pb.wyverndice.wyverndicediceset.model.DiceSet;
import com.pb.wyverndice.wyverndicediceset.repository.DiceSetRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiceSetService {

    private final DiceSetRepository diceSetRepository;

    public DiceSetService(DiceSetRepository diceSetRepository) {
        this.diceSetRepository = diceSetRepository;
    }

    public List<DiceSet> getAllDiceSets(){
        return diceSetRepository.findAll();
    }

    public DiceSet getDiceSetById(Long id){
        return diceSetRepository.findById(id).orElse(null);
    }

    public DiceSet createDiceSet(DiceSet diceSet){
        DiceSet createdSet = diceSetRepository.save(diceSet);
        return createdSet;
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
        DiceSet diceSet = diceSetRepository.findById(id).orElse(null);
        if (diceSet != null) {
            newDiceSet.setId(id);
            diceSetRepository.save(newDiceSet);
        }
    }

}
