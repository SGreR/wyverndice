package com.pb.wyverndice.wyverndicediceset;

import com.pb.wyverndice.wyverndicediceset.enums.*;
import com.pb.wyverndice.wyverndicediceset.model.DiceSet;
import com.pb.wyverndice.wyverndicediceset.model.Die;
import com.pb.wyverndice.wyverndicediceset.repository.DiceSetRepository;
import com.pb.wyverndice.wyverndicediceset.repository.DieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;
import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(DiceSetRepository diceSetRepository, DieRepository dieRepository) {
        return args -> {

            List<Die> dice = listOf(
                    new Die(NumberOfSides.TWENTY, listOf(Colors.PURPLE), DiceStyle.TRANSLUCENT, Colors.OLD_GOLD, null),
                    new Die(NumberOfSides.TWENTY, listOf(Colors.RED), DiceStyle.SOLID, Colors.GOLD, null),
                    new Die(NumberOfSides.TWENTY, listOf(Colors.COPPER), DiceStyle.SMOKE, Colors.SILVER, null)
            );

            dieRepository.saveAll(dice);

            DiceSet set1 = new DiceSet("Classic Set", new ArrayList<>(), listOf(Colors.BLACK), Colors.GOLD, DiceStyle.SOLID, NumberOfDice.EIGHT);

            diceSetRepository.save(set1);
        };
    }
}
