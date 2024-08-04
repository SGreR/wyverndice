package com.pb.wyverndice.wyverndicepurchases.enums;

import lombok.Getter;

@Getter
public enum ProductType {
    PREMADE("Premade Set"),
    CUSTOM("Custom Set"),
    DIE("Individual Die");

    private final String productType;

    ProductType(String productType) {
        this.productType = productType;
    }


}
