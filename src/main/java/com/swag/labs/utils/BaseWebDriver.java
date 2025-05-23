package com.swag.labs.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseWebDriver {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (BaseWebDriver.class) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized")
                        .addArguments("--incognito")
                        .addArguments("--guest")
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-default-apps")
                        .addArguments("--headless=new");

                driver = new ChromeDriver(options);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
