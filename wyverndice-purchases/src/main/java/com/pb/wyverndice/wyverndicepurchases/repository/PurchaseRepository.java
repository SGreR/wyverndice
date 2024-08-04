package com.pb.wyverndice.wyverndicepurchases.repository;


import com.pb.wyverndice.wyverndicepurchases.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
