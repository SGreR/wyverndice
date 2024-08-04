package com.pb.wyverndice.wyverndicediceset;

import com.pb.wyverndice.wyverndicediceset.enums.*;
import com.pb.wyverndice.wyverndicediceset.model.DiceSet;
import com.pb.wyverndice.wyverndicediceset.model.Die;
import com.pb.wyverndice.wyverndicediceset.model.Product;
import com.pb.wyverndice.wyverndicediceset.repository.DiceSetRepository;
import com.pb.wyverndice.wyverndicediceset.repository.DieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;
import static org.hibernate.internal.util.collections.CollectionHelper.setOf;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(DiceSetRepository diceSetRepository, DieRepository dieRepository) {
        return args -> {

            List<Die> dice = listOf(
                    new Die(ProductType.DIE, NumberOfSides.TWENTY, listOf(Colors.PURPLE), DiceStyle.TRANSLUCENT, Colors.OLD_GOLD, null, 0.0),
                    new Die(ProductType.DIE, NumberOfSides.TWENTY, listOf(Colors.RED), DiceStyle.SOLID, Colors.GOLD, null, 0.0),
                    new Die(ProductType.DIE, NumberOfSides.TWENTY, listOf(Colors.COPPER), DiceStyle.SMOKE, Colors.SILVER, null, 0.0)
            );

            dieRepository.saveAll(dice);

            DiceSet set1 = new DiceSet("Classic Set", new ArrayList<>(), ProductType.PREMADE, listOf(Colors.BLACK), Colors.GOLD, DiceStyle.SOLID, NumberOfDice.EIGHT, 0.0);
            set1.populateDice();

            diceSetRepository.save(set1);
        };
    }
}
