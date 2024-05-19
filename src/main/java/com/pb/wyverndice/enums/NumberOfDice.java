package com.pb.wyverndice.enums;

import lombok.Getter;

@Getter
public enum NumberOfDice {
    EIGHT(8),
    TEN(10);

    private final int value;

    NumberOfDice(int value) {
        this.value = value;
    }

}
