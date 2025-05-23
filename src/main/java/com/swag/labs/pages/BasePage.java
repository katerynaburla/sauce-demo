package com.swag.labs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.util.Objects.requireNonNull;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.actions = new Actions(driver);
    }

    protected void setValue(WebElement input, String value) {
        if (!(requireNonNull(input.getDomAttribute("value"))).isEmpty()) {
            input.clear();
        }
        actions.sendKeys(input, value).perform();
    }
}
