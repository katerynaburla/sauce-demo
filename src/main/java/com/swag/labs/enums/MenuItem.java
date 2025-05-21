package com.swag.labs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuItem {
    ALL_ITEMS("All Items", "inventory_sidebar_link"),
    LOGOUT("Logout", "logout_sidebar_link"),
    RESET_APP_STATE("Reset App State", "reset_sidebar_link");

    private final String message;
    private final String id;
}