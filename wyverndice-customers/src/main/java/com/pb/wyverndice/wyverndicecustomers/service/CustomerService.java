package com.pb.wyverndice.wyverndicecustomers.service;

import com.pb.wyverndice.wyverndicecustomers.filter.CustomerFilters;
import com.pb.wyverndice.wyverndicecustomers.model.Customer;
import com.pb.wyverndice.wyverndicecustomers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public List<Customer> getAllCustomersByName(CustomerFilters filters){
        return customerRepository.findAllByName(filters.getName().orElse(null), filters.getEmail().orElse(null));
    }

    public List<Customer> getAllCustomersByRole(CustomerFilters filters){
        return customerRepository.findAllByRoles(filters.getRole().get());
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer){
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, Customer newCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            newCustomer.setId(id);
            customerRepository.save(newCustomer);
        }
    }
}
