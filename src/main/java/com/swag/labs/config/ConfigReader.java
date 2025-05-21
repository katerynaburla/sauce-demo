package com.swag.labs.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {

    private static final AppConfig config = ConfigFactory.create(AppConfig.class);

    private ConfigReader() {
    }

    public static String getBaseUrl() {
        return config.url();
    }

    public static String getPassword() {
        return config.password();
    }

    public static String getStandardUser() {
        return config.standardUser();
    }

    public static String getLockedOutUser() {
        return config.lockedOutUser();
    }

    public static String getProblemUser() {
        return config.problemUser();
    }

    public static String getErrorUser() {
        return config.errorUser();
    }

    public static String getVisualUser() {
        return config.visualUser();
    }

    public static String getPerformanceGlitchUser() {
        return config.performanceGlitchUser();
    }

    public static String getFirstName() {
        return config.firstName();
    }

    public static String getLastName() {
        return config.lastName();
    }

    public static String getPostalCode() {
        return config.postalCode();
    }
}
