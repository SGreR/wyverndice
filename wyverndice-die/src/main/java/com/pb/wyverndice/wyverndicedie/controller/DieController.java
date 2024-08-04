package com.pb.wyverndice.wyverndicedie.controller;

import com.pb.wyverndice.wyverndicedie.exception.ResourceNotFoundException;
import com.pb.wyverndice.wyverndicedie.filter.DieFilters;
import com.pb.wyverndice.wyverndicedie.model.Die;
import com.pb.wyverndice.wyverndicedie.payload.MessagePayload;
import com.pb.wyverndice.wyverndicedie.service.DieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/die")
public class DieController {

    private final DieService dieService;

    public DieController(DieService dieService) {
        this.dieService = dieService;
    }

    @GetMapping
    public ResponseEntity<List<Die>> getAll(@ModelAttribute DieFilters filters){
        if (filters.getMainColor().isPresent() || filters.getNumberColor().isPresent() ||
                filters.getSides().isPresent() || filters.getStyle().isPresent()) {
            return ResponseEntity.ok(dieService.getDiceWithFilters(filters));
        } else {
            return ResponseEntity.ok(dieService.getAllDice());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            Die encontrado = dieService.getDieById(id);
            return ResponseEntity.ok(encontrado);
        }catch (ResourceNotFoundException ex){
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Die die){
        dieService.createDie(die);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Long id, @RequestBody Die die){
        try {
            dieService.updateDie(id, die);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Long id){
        try {
            dieService.deleteDieById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
