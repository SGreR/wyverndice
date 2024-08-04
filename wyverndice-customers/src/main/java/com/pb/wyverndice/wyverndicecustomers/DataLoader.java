package com.pb.wyverndice.wyverndicecustomers;

import com.pb.wyverndice.wyverndicecustomers.enums.Role;
import com.pb.wyverndice.wyverndicecustomers.model.Address;
import com.pb.wyverndice.wyverndicecustomers.model.Customer;
import com.pb.wyverndice.wyverndicecustomers.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(CustomerRepository customerRepository) {
        return args -> {

            Customer customer1 = new Customer();
            customer1.setName("John Doe");
            customer1.setEmail("john.doe@example.com");
            customer1.setPassword("password123");
            customer1.setRole(Role.Client);

            Customer customer2 = new Customer();
            customer2.setName("Jane Smith");
            customer2.setEmail("jane.smith@example.com");
            customer2.setPassword("password456");
            customer2.setRole(Role.Admin);

            Address address1 = new Address();
            address1.setNumber("123");
            address1.setStreet("123 Main St");
            address1.setCity("Springfield");
            address1.setState("IL");
            address1.setCountry("USA");
            address1.setZipCode("62701");
            address1.setCustomer(customer1);

            Address address2 = new Address();
            address2.setNumber("456");
            address2.setStreet("456 Elm St");
            address2.setCity("Springfield");
            address2.setState("IL");
            address2.setCountry("USA");
            address2.setZipCode("62702");
            address2.setCustomer(customer2);

            customer1.setAddresses(listOf(address1));
            customer2.setAddresses(listOf(address2));

            customerRepository.save(customer1);
            customerRepository.save(customer2);
        };
    }
}
