package com.pb.wyverndice.wyverndicecustomers.repository;

import com.pb.wyverndice.wyverndicecustomers.enums.Role;
import com.pb.wyverndice.wyverndicecustomers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE (:customerName is null or c.name LIKE %:customerName%) " +
            "AND (:customerEmail is null or c.email LIKE %:customerEmail%) ORDER BY c.name DESC")
    List<Customer> findAllByName(@Param("customerName") String name, @Param("customerEmail") String email);

    @Query("SELECT c FROM Customer c WHERE c.role=:role")
    List<Customer> findAllByRoles(@Param("role") Role role);
}
