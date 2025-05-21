package com.swag.labs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CheckoutOverviewPage extends HomePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutOverviewPage.class);
    private final WebElement cancelButton = driver.findElement(By.id("cancel"));
    private final WebElement finishButton = driver.findElement(By.id("finish"));

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(this.finishButton));
    }

    @Step("Check whether the 'Checkout: Overview' page is opened")
    public boolean isPageOpened() {
        boolean isDisplayed = this.headerTitle.getText().equalsIgnoreCase("Checkout: Overview");
        logger.info("The 'Checkout: Overview' page is opened: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Get 'Checkout overview' items amount")
    public int getCartItemsAmount() {
        int numberOfItems = getListOfCartItems().size();
        logger.info("There are {} items in the 'Checkout overview'", numberOfItems);
        return numberOfItems;
    }

    @Step("Click the 'Cancel' button")
    public ProductsPage clickCancelShoppingButton() {
        cancelButton.click();
        logger.info("Clicked the 'Cancel' button");
        return new ProductsPage(driver);
    }

    @Step("Click the 'Finish' button")
    public CheckoutCompletePage clickFinishShoppingButton() {
        finishButton.click();
        logger.info("Clicked the 'Finish' button");
        return new CheckoutCompletePage(driver);
    }

    private List<WebElement> getListOfCartItems() {
        return driver.findElements(By.className("cart_item"));
    }
}

