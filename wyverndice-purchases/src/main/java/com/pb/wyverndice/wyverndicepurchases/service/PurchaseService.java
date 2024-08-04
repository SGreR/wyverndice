package com.pb.wyverndice.wyverndicepurchases.service;

import com.pb.wyverndice.wyverndicepurchases.model.Purchase;
import com.pb.wyverndice.wyverndicepurchases.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id){
        return purchaseRepository.findById(id).orElse(null);
    }

    public Purchase createPurchase(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public void deletePurchaseById(Long id){
        purchaseRepository.deleteById(id);
    }

    public void updatePurchase(Long id, Purchase newPurchase) {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase != null) {
            newPurchase.setId(id);
            purchaseRepository.save(newPurchase);
        }
    }
}
