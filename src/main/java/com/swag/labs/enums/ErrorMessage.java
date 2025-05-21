package com.swag.labs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    DO_NOT_MATCH_ANY("Epic sadface: Username and password do not match any user in this service"),
    USERNAME_IS_REQUIRED("Epic sadface: Username is required"),
    PASSWORD_IS_REQUIRED("Epic sadface: Password is required"),
    LOCKED_OUT_USER("Epic sadface: Sorry, this user has been locked out.");

    private final String message;
}
