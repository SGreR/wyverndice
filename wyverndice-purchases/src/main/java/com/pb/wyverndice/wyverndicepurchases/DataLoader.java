package com.pb.wyverndice.wyverndicepurchases;

import com.pb.wyverndice.wyverndicepurchases.enums.ProductType;
import com.pb.wyverndice.wyverndicepurchases.model.Purchase;
import com.pb.wyverndice.wyverndicepurchases.model.Cart;
import com.pb.wyverndice.wyverndicepurchases.model.Product;
import com.pb.wyverndice.wyverndicepurchases.model.Customer;
import com.pb.wyverndice.wyverndicepurchases.repository.PurchaseRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;
import static org.hibernate.internal.util.collections.CollectionHelper.setOf;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(PurchaseRepository purchaseRepository) {
        return args -> {
            Purchase purchase = new Purchase();
            purchase.setCustomerId(1L);
            purchase.setProducts(listOf(new Product(1L, ProductType.PREMADE, 145.0), new Product(2L, ProductType.PREMADE, 125.0)));
            purchase.setProductIds(setOf(1L, 2L));

            purchaseRepository.save(purchase);

        };
    }
}
