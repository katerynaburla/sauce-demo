package com.swag.labs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortingType {

    NAME_A_Z("Name (A to Z)"),
    PRICE_LOW_TO_HIGH("Price (low to high)");

    private final String type;
}
