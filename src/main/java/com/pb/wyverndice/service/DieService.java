package com.pb.wyverndice.service;

import com.pb.wyverndice.filters.DieFilters;
import com.pb.wyverndice.model.Die;
import com.pb.wyverndice.repository.DieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DieService {

    private final DieRepository dieRepository;
    private final EntityManager entityManager;

    public DieService(DieRepository dieRepository, EntityManager entityManager) {
        this.dieRepository = dieRepository;
        this.entityManager = entityManager;
    }

    public List<Die> getAllDice(){
        return dieRepository.findAll();
    }

    public Die getDieById(Long id){
        return dieRepository.findById(id).orElse(null);
    }

    public List<Die> getDiceWithFilters(DieFilters filters){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Die> cq = cb.createQuery(Die.class);
        Root<Die> die = cq.from(Die.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getMainColor().isPresent()) {
            String mainColorQuery = filters.getMainColor().get();
            MapJoin<Die, String, String> join = die.joinMap ("colors");
            Predicate mainColor = cb.and(cb.equal(join.key(), "Main"), cb.equal(join.value(), mainColorQuery));
            predicates.add(mainColor);
        }

        if (filters.getNumberColor().isPresent()) {
            String numberColorQuery = filters.getNumberColor().get() + "%";
            Predicate numberColors = cb.like(die.get("numberColors"), numberColorQuery);
            predicates.add(numberColors);
        }

        if (filters.getSides().isPresent()) {
            Predicate sides = cb.equal(die.get("sides"), filters.getSides().get());
            predicates.add(sides);
        }

        if (filters.getStyle().isPresent()) {
            Predicate style = cb.equal(die.get("style"), filters.getStyle().get());
            predicates.add(style);
        }

        cq.where(predicates.toArray(Predicate[]::new));
        List<Die> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }

    public Die createDie(Die die){
        return dieRepository.save(die);
    }

    public void deleteDieById(Long id){
        dieRepository.deleteById(id);
    }

    public void updateDie(Long id, Die newDie) {
        Die die = dieRepository.findById(id).orElse(null);
        if (die != null) {
            die.setSides(newDie.getSides());
            die.setStyle(newDie.getStyle());
            die.setColors(newDie.getColors());
            die.setNumberColors(newDie.getNumberColors());
            die.setDiceSet(newDie.getDiceSet());
            dieRepository.save(die);
        }
    }

}
