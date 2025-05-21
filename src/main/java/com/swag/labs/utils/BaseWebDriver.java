package com.swag.labs.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseWebDriver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
//            options.addArguments("--test-type");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--ignore-certificate-errors");
//            options.addArguments("--disable-popup-blocking");
//            options.addArguments("--disable-save-password-bubble");

            options.addArguments("--disable-default-apps");
            options.addArguments("--incognito");
            driver.set(new ChromeDriver(options));
        }
        return driver.get();
    }

    public static void quit() {
        driver.get().quit();
        driver.set(null);
    }
}
