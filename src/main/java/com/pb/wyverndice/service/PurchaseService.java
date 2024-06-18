package com.pb.wyverndice.service;

import com.pb.wyverndice.model.Purchase;
import com.pb.wyverndice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id){
        return purchaseRepository.findById(id).orElse(null);
    }

    public Purchase createPurchase(Purchase purchase){
        Purchase newPurchase = new Purchase();
        Long id = purchaseRepository.save(newPurchase).getId();
        newPurchase.setId(id);
        newPurchase.setCustomer(purchase.getCustomer());
        newPurchase.setDiceSets(purchase.getDiceSets());
        newPurchase.setDeliveryFee(purchase.getDeliveryFee());
        return purchaseRepository.save(newPurchase);
    }

    public void deletePurchaseById(Long id){
        purchaseRepository.deleteById(id);
    }

    public void updatePurchase(Long id, Purchase newPurchase) {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase != null) {
            purchase.setCustomer(newPurchase.getCustomer());
            purchase.setDiceSets(newPurchase.getDiceSets());
            purchaseRepository.save(purchase);
        }
    }
}
