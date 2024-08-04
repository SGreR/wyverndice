package com.pb.wyverndice.wyverndicediceset.filter;


import com.pb.wyverndice.wyverndicediceset.enums.DiceStyle;
import com.pb.wyverndice.wyverndicediceset.enums.NumberOfSides;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DieFilters {
    private Optional<String> mainColor = Optional.empty();
    private Optional<String> numberColor = Optional.empty();
    private Optional<NumberOfSides> sides = Optional.empty();
    private Optional<DiceStyle> style = Optional.empty();
}