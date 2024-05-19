package com.pb.wyverndice.controller;

import com.pb.wyverndice.exception.ResourceNotFoundException;
import com.pb.wyverndice.model.DiceSet;
import com.pb.wyverndice.payload.MessagePayload;
import com.pb.wyverndice.service.DiceSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/diceset")
public class DiceSetController {
    final DiceSetService diceSetService;

    public DiceSetController(DiceSetService diceSetService) {
        this.diceSetService = diceSetService;
    }

    @GetMapping
    public ResponseEntity<List<DiceSet>> getAll(@RequestParam(required = false) Optional<String> name){
        if(name.isEmpty()){
            return ResponseEntity.ok(diceSetService.getAll());
        }else {
            List<DiceSet> diceSets = diceSetService.filterByName(name.get());
            if(diceSets.isEmpty()){
                return ResponseEntity.notFound().build();
            }else{
                return ResponseEntity.ok(diceSets);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try{
            DiceSet encontrado = diceSetService.getById(id);
            return ResponseEntity.ok(encontrado);
        }catch (ResourceNotFoundException ex){
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody DiceSet diceSet){
        diceSetService.save(diceSet);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody DiceSet diceSet){
        try {
            diceSetService.update(id,diceSet);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id){
        try {
            diceSetService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }


}
