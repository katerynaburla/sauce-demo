package com.swag.labs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ThreadGuard;

public class BaseWebDriver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private BaseWebDriver() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized")
                    .addArguments("--incognito")
                    .addArguments("--guest")
                    .addArguments("--no-sandbox")
                    .addArguments("--disable-default-apps")
                    .addArguments("--headless=new");
            driver.set(ThreadGuard.protect(new ChromeDriver(options)));
        }
        return driver.get();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
