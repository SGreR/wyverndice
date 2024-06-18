package com.pb.wyverndice.repository;

import com.pb.wyverndice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
