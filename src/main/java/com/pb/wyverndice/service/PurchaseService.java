package com.pb.wyverndice.service;

import com.pb.wyverndice.model.DBLog;
import com.pb.wyverndice.model.Purchase;
import com.pb.wyverndice.repository.DBLogRepository;
import com.pb.wyverndice.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final DBLogRepository logRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, DBLogRepository logRepository) {
        this.purchaseRepository = purchaseRepository;
        this.logRepository = logRepository;
    }

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
        logRepository.save(new DBLog(Purchase.class.getName(), newPurchase.getId(), "Created"));
        return purchaseRepository.save(newPurchase);
    }

    public void deletePurchaseById(Long id){
        purchaseRepository.deleteById(id);
        logRepository.save(new DBLog(Purchase.class.getName(), id, "Deleted"));
    }

    public void updatePurchase(Long id, Purchase newPurchase) {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase != null) {
            newPurchase.setId(id);
            purchaseRepository.save(newPurchase);
            logRepository.save(new DBLog(Purchase.class.getName(), id, "Updated"));
        }
    }
}
