package com.pb.wyverndice.controller;

import com.pb.wyverndice.exception.ResourceNotFoundException;
import com.pb.wyverndice.filters.CustomerFilters;
import com.pb.wyverndice.model.Customer;
import com.pb.wyverndice.payload.MessagePayload;
import com.pb.wyverndice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(@ModelAttribute CustomerFilters filters){
        if (filters.getRole().isPresent()){
            return ResponseEntity.ok(customerService.getAllCustomersByRole(filters));
        }
        else if (filters.getName().isPresent() || filters.getEmail().isPresent()) {
            return ResponseEntity.ok(customerService.getAllCustomersByName(filters));
        } else {
            return ResponseEntity.ok(customerService.getAllCustomers());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            Customer encontrado = customerService.getCustomerById(id);
            return ResponseEntity.ok(encontrado);
        }catch (ResourceNotFoundException ex){
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Customer customer){
        try{
            customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
        }catch (Exception ex){
            String message = ex.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(message));
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Long id, @RequestBody Customer customer){
        try {
            customerService.updateCustomer(id, customer);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Long id){
        try {
            customerService.deleteCustomerById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
