package com.pb.wyverndice.wyverndicedie.enums;

import lombok.Getter;

@Getter
public enum NumberOfSides {
    TWO(2),
    FOUR(4),
    SIX(6),
    EIGHT(8),
    TEN(10),
    PERCENTILE(10),
    TWELVE(12),
    TWENTY(20);

    private final int sides;

    NumberOfSides(int sides) {
        this.sides = sides;
    }

}
