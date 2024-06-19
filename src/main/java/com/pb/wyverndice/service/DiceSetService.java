package com.pb.wyverndice.service;

import com.pb.wyverndice.model.DBLog;
import com.pb.wyverndice.model.DiceSet;
import com.pb.wyverndice.model.PremadeDiceSet;
import com.pb.wyverndice.repository.DBLogRepository;
import com.pb.wyverndice.repository.DiceSetRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiceSetService {

    private final DiceSetRepository diceSetRepository;
    private final DBLogRepository logRepository;

    public DiceSetService(DiceSetRepository diceSetRepository, DBLogRepository dbLogRepository) {
        this.diceSetRepository = diceSetRepository;
        this.logRepository = dbLogRepository;
    }

    public List<DiceSet> getAllDiceSets(){
        return diceSetRepository.findAll();
    }

    public DiceSet getDiceSetById(Long id){
        return diceSetRepository.findById(id).orElse(null);
    }

    public DiceSet createDiceSet(DiceSet diceSet){
        DiceSet createdSet = diceSetRepository.save(diceSet);
        logRepository.save(new DBLog(DiceSet.class.getName(), createdSet.id, "Created"));
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
        logRepository.save(new DBLog(DiceSet.class.getName(), id, "Deleted"));
    }

    public void updateDiceSet(Long id, DiceSet newDiceSet) {
        PremadeDiceSet newSet = (PremadeDiceSet) newDiceSet;
        PremadeDiceSet diceSet = (PremadeDiceSet) diceSetRepository.findById(id).orElse(null);
        if (diceSet != null) {
            newSet.setId(id);
            diceSetRepository.save(newSet);
            logRepository.save(new DBLog(DiceSet.class.getName(), id, "Updated"));
        }
    }

}
