package com.pb.wyverndice;

import com.pb.wyverndice.enums.DiceStyle;
import com.pb.wyverndice.enums.NumberOfDice;
import com.pb.wyverndice.enums.NumberOfSides;
import com.pb.wyverndice.enums.Role;
import com.pb.wyverndice.model.*;
import com.pb.wyverndice.repository.DiceSetRepository;
import com.pb.wyverndice.repository.DieRepository;
import com.pb.wyverndice.repository.PurchaseRepository;
import com.pb.wyverndice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DieRepository dieRepository;
    @Autowired
    private DiceSetRepository diceSetRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void run(String... args) throws Exception {

        Customer customer1 = new Customer("João", "joao@gmail.com", "testpassword", Role.Client);
        Customer customer2 = new Customer("Anna", "anna@gmail.com", "testpassword", Role.Client);
        customerRepository.saveAll(listOf(customer1, customer2));

        Die die1 = new Die(NumberOfSides.TWENTY);
        Die die2 = new Die(NumberOfSides.TEN);
        dieRepository.saveAll(listOf(die1, die2));

        List<PremadeDiceSet> diceSets = listOf(
                new PremadeDiceSet("Classic Set", new HashMap<>(Map.of("Main", "Black")), "Gold", DiceStyle.SOLID, NumberOfDice.TEN),
                new PremadeDiceSet("Smoky Set", new HashMap<>(Map.of("Main", "Clear", "Secondary", "Blue")), "Gold", DiceStyle.SMOKE, NumberOfDice.TEN),
                new PremadeDiceSet("Fantasy Set", new HashMap<>(Map.of("Main", "Red", "Secondary", "Gold", "Tertiary", "Black")), "White", DiceStyle.MARBLED, NumberOfDice.EIGHT),
                new PremadeDiceSet("Metallic Set", new HashMap<>(Map.of("Main", "Blue", "Secondary", "Purple")), "Silver", DiceStyle.TRANSLUCENT, NumberOfDice.TEN)
        );
        for (PremadeDiceSet set : diceSets) {
            Long id = diceSetRepository.save(set).getId();
            set.setId(id);
        }

        Cart cart1 = new Cart(listOf(diceSets.get(0), diceSets.get(1)));
        Cart cart2 = new Cart(listOf(diceSets.get(2)));

        Purchase purchase1 = new Purchase(cart1, customer1);
        Purchase purchase2 = new Purchase(cart2, customer2);
        purchaseRepository.saveAll(listOf(purchase1, purchase2));

    }
}
