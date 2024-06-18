package com.pb.wyverndice.repository;

import com.pb.wyverndice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
