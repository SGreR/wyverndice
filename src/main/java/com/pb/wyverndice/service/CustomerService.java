package com.pb.wyverndice.service;

import com.pb.wyverndice.filters.CustomerFilters;
import com.pb.wyverndice.model.Customer;
import com.pb.wyverndice.model.DBLog;
import com.pb.wyverndice.repository.CustomerRepository;
import com.pb.wyverndice.repository.DBLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DBLogRepository logRepository;

    public CustomerService(CustomerRepository customerRepository, DBLogRepository logRepository) {
        this.customerRepository = customerRepository;
        this.logRepository = logRepository;
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
        logRepository.save(new DBLog(Customer.class.getName(), createdCustomer.getId(), "Created"));
        return createdCustomer;
    }

    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
        logRepository.save(new DBLog(Customer.class.getName(), id, "Deleted"));
    }

    public void updateCustomer(Long id, Customer newCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            newCustomer.setId(id);
            customerRepository.save(newCustomer);
            logRepository.save(new DBLog(Customer.class.getName(), id, "Updated"));
        }
    }
}
