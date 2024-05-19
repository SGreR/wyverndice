package com.pb.wyverndice.enums;

import lombok.Getter;

@Getter
public enum DiceStyle {
    SOLID(1),
    MARBLED(1.15),
    TRANSLUCENT(1),
    SMOKE(1.20);

    private final double multiplier;

    DiceStyle(double multiplier) {
        this.multiplier = multiplier;
    }

}
