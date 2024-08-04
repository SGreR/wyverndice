package com.pb.wyverndice.wyverndicediceset;

import com.pb.wyverndice.wyverndicediceset.enums.Colors;
import com.pb.wyverndice.wyverndicediceset.enums.DiceStyle;
import com.pb.wyverndice.wyverndicediceset.enums.NumberOfDice;
import com.pb.wyverndice.wyverndicediceset.model.DiceSet;
import com.pb.wyverndice.wyverndicediceset.model.PremadeDiceSet;
import com.pb.wyverndice.wyverndicediceset.service.DiceSetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class DiceSetServiceTests {
    @Autowired
    DiceSetService diceSetService;

    @Test
    @DisplayName("Deve inserir um cojunto de dados no banco.")
    public void testInsertDiceSet(){
        List<DiceSet> all = diceSetService.getAllDiceSets();
        int initialState = all.size();
        PremadeDiceSet diceSet = new PremadeDiceSet("Classic Set", listOf(Colors.BLACK) , Colors.GOLD, DiceStyle.SOLID, NumberOfDice.TEN);
        diceSet.setId(initialState + 1L);
        diceSetService.createDiceSet(diceSet);
        all = diceSetService.getAllDiceSets();
        int endState = all.size();
        assertEquals(initialState + 1, endState);
    }

    @Test
    @DisplayName("Deve deletar um conjunto de dados do banco")
    public void testDeleteDiceSet(){
        PremadeDiceSet diceSet = new PremadeDiceSet("Classic Set", listOf(Colors.BLACK), Colors.GOLD, DiceStyle.SOLID, NumberOfDice.TEN);
        diceSet.setId(5L);
        diceSetService.createDiceSet(diceSet);
        List<DiceSet> all = diceSetService.getAllDiceSets();
        int initialState = all.size();
        DiceSet diceSetInDb = all.stream()
                .filter(ds -> ds.getId().equals(diceSet.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dice set not found"));
        diceSetService.deleteDiceSetById(diceSetInDb.getId());
        all = diceSetService.getAllDiceSets();
        int endState = all.size();
        assertEquals(initialState - 1, endState);
    }

    @Test
    @DisplayName("Deve retornar um conjunto de dados pelo ID")
    public void testGetDiceSetById(){
        PremadeDiceSet diceSet = new PremadeDiceSet("Classic Set", listOf(Colors.BLACK), Colors.GOLD, DiceStyle.SOLID, NumberOfDice.TEN);
        diceSet = (PremadeDiceSet) diceSetService.createDiceSet(diceSet);
        PremadeDiceSet diceSetInDb = (PremadeDiceSet) diceSetService.getDiceSetById(diceSet.getId());
        assertNotNull(diceSetInDb);
    }


}
