package com.swag.labs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutPage extends HomePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutPage.class);
    private final WebElement firstNameInput = driver.findElement(By.id("first-name"));
    private final WebElement lastNameInput = driver.findElement(By.id("last-name"));
    private final WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
    private final WebElement cancelButton = driver.findElement(By.id("cancel"));
    private final WebElement continueButton = driver.findElement(By.id("continue"));

    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
    }

    @Step("Check whether the 'Checkout: Your Information' page is opened")
    public boolean isPageOpened() {
        boolean isDisplayed = this.headerTitle.getText().equalsIgnoreCase("Checkout: Your Information");
        logger.info("The 'Checkout: Your Information' page is opened: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Set checkout info: '{name}', {password}, {code}")
    public void setCredentials(String name, String password, String code) {
        logger.info("Set username: {} and password: {}", name, password);
        setValue(firstNameInput, name);
        setValue(lastNameInput, password);
        setValue(postalCodeInput, code);
        continueButton.click();
    }

    @Step("Click the 'Cancel' button")
    public CartPage clickCancelShoppingButton() {
        cancelButton.click();
        logger.info("Clicked the 'Cancel' button");
        return new CartPage(driver);
    }
}

