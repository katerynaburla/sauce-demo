package com.swag.labs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutCompletePage extends HomePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutCompletePage.class);
    private final WebElement checkoutCompleteStatus = driver.findElement(By.className("complete-header"));
    private final WebElement backHomeButton = driver.findElement(By.id("back-to-products"));

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(this.backHomeButton));
    }

    @Step("Check whether the 'Checkout: Complete!' page is opened")
    public boolean isPageOpened() {
        boolean isDisplayed = this.headerTitle.getText().equalsIgnoreCase("Checkout: Complete!");
        logger.info("Checkout: Complete!: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Verify the 'Checkout Complete' status")
    public String getCheckoutStatus() {
        String status = checkoutCompleteStatus.getText();
        logger.info("'Checkout Complete' status: {}", status);
        return status;
    }

    @Step("Click the 'Back Home' button")
    public ProductsPage clickBackHomeButton() {
        backHomeButton.click();
        logger.info("Clicked the 'Back Home' button");
        return new ProductsPage(driver);
    }
}

