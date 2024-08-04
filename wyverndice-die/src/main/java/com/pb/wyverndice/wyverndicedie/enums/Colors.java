package com.pb.wyverndice.wyverndicedie.enums;

import lombok.Getter;

@Getter
public enum Colors {
    BLACK("Black"),
    WHITE("White"),
    RED("Red"),
    RED_LIGHT("Light Red"),
    RED_DARK("Dark Red"),
    BLUE("Blue"),
    BLUE_LIGHT("Light Blue"),
    BLUE_DARK("Dark Blue"),
    GREEN("Green"),
    GREEN_LIGHT("Light Green"),
    GREEN_DARK("Dark Green"),
    YELLOW("Yellow"),
    YELLOW_LIGHT("Light Yellow"),
    YELLOW_DARK("Dark Yellow"),
    ORANGE("Orange"),
    ORANGE_LIGHT("Light Orange"),
    ORANGE_DARK("Dark Orange"),
    PURPLE("Purple"),
    PURPLE_LIGHT("Light Purple"),
    PURPLE_DARK("Dark Purple"),
    PINK("Pink"),
    PINK_LIGHT("Light Pink"),
    PINK_DARK("Dark Pink"),
    BROWN("Brown"),
    BROWN_LIGHT("Light Brown"),
    BROWN_DARK("Dark Brown"),
    GRAY("Gray"),
    GRAY_LIGHT("Light Gray"),
    GRAY_DARK("Dark Gray"),
    GOLD("Gold"),
    OLD_GOLD("Old Gold"),
    COPPER("Copper"),
    SILVER("Silver"),
    BRONZE("Bronze"),
    PLATINUM("Platinum"),
    ROSE_GOLD("Rose Gold");

    private final String colorName;

    Colors(String colorName) {
        this.colorName = colorName;
    }

}

