package com.pb.wyverndice.service;

import com.pb.wyverndice.model.Customer;
import com.pb.wyverndice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, Customer newCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setName(newCustomer.getName());
            customer.setEmail(newCustomer.getEmail());
            customer.setPassword(newCustomer.getPassword());
            customer.setRole(newCustomer.getRole());
            customerRepository.save(customer);
        }
    }
}
