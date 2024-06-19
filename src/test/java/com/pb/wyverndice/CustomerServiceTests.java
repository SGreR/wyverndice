package com.pb.wyverndice;

import com.pb.wyverndice.enums.Role;
import com.pb.wyverndice.model.Customer;
import com.pb.wyverndice.service.CustomerService;
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
public class CustomerServiceTests {
    @Autowired
    CustomerService customerService;

    @Test
    @DisplayName("Deve inserir um usuário no banco.")
    public void testInsertCustomer(){
        List<Customer> all = customerService.getAllCustomers();
        int initialState = all.size();
        Customer customer = new Customer();
        customer.setId(initialState + 1L);
        customer.setName("Pedro");
        customer.setEmail("pedro@gmail.com");
        customer.setPassword("testpassword");
        customer.setRole(Role.Client);
        customerService.createCustomer(customer);
        all = customerService.getAllCustomers();
        int endState = all.size();
        assertEquals(initialState + 1, endState);
    }

    @Test
    @DisplayName("Deve deletar um usuário do banco")
    public void testDeleteCustomer(){
        Customer customer = new Customer();
        customer.setName("Pedro");
        customer.setEmail("pedro@gmail.com");
        customer.setPassword("testpassword");
        customer.setRole(Role.Client);
        Customer customerInDb = customerService.createCustomer(customer);
        List<Customer> all = customerService.getAllCustomers();
        int initialState = all.size();
        customerService.deleteCustomerById(customerInDb.getId());
        all = customerService.getAllCustomers();
        int endState = all.size();
        assertEquals(initialState - 1, endState);
    }

    @Test
    @DisplayName("Deve retornar um usuário pelo ID")
    public void testGetById(){
        Customer customer = new Customer();
        customer.setName("Pedro");
        customer.setEmail("pedro@gmail.com");
        customer.setPassword("testpassword");
        customer.setRole(Role.Client);
        customer = customerService.createCustomer(customer);
        Customer customerInDb = customerService.getCustomerById(customer.getId());
        assertNotNull(customerInDb);
    }


}
