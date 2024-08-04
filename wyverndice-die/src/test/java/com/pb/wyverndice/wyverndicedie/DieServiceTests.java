package com.pb.wyverndice.wyverndicedie;

import com.pb.wyverndice.wyverndicedie.enums.NumberOfSides;
import com.pb.wyverndice.wyverndicedie.model.Die;
import com.pb.wyverndice.wyverndicedie.service.DieService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Slf4j
public class DieServiceTests {

    @Autowired
    DieService dieService;

    @Test
    @DisplayName("Deve inserir um dado no banco.")
    public void testInsertDie(){
        List<Die> all = dieService.getAllDice();
        int initialState = all.size();
        Die die = new Die();
        die.setSides(NumberOfSides.TWENTY);
        die.setId(initialState + 1L);
        dieService.createDie(die);
        all = dieService.getAllDice();
        int endState = all.size();
        assertEquals(initialState + 1, endState);
    }

    @Test
    @DisplayName("Deve deletar um dado do banco")
    public void testDeleteCustomer(){
        Die die = new Die();
        die.setSides(NumberOfSides.TWENTY);
        Die dieInDb = dieService.createDie(die);
        List<Die> all = dieService.getAllDice();
        int initialState = all.size();
        dieService.deleteDieById(dieInDb.getId());
        all = dieService.getAllDice();
        int endState = all.size();
        assertEquals(initialState - 1, endState);
    }

    @Test
    @DisplayName("Deve retornar um dado pelo ID")
    public void testGetById(){
        Die die = new Die();
        die.setSides(NumberOfSides.TWENTY);
        die = dieService.createDie(die);
        Die dieInDb = dieService.getDieById(die.getId());
        assertNotNull(dieInDb);
    }
}
