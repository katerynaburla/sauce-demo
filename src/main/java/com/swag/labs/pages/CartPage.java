package com.swag.labs.pages;

import com.swag.labs.enums.MenuItem;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CartPage extends HomePage {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);
    private final WebElement checkoutButton = driver.findElement(By.id("checkout"));
    private final WebElement continueShoppingButton = driver.findElement(By.id("continue-shopping"));

    public CartPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(this.checkoutButton));
    }

    @Step("Check whether the 'Your Cart' page is opened")
    public boolean isPageOpened() {
        boolean isDisplayed = this.headerTitle.getText().equalsIgnoreCase("Your Cart");
        logger.info("The 'Your Cart' page is opened: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Get cart items amount")
    public int getCartItemsAmount() {
        int numberOfItemsInTheCart = getListOfCartItems().size();
        logger.info("There are {} items in the cart", numberOfItemsInTheCart);
        return numberOfItemsInTheCart;
    }

    @Step("Go to 'All items' page")
    public ProductsPage gotoAlItemsPage() {
        this.selectMenuItem(MenuItem.ALL_ITEMS);
        logger.info("Clicked the {} button", MenuItem.ALL_ITEMS.getMessage());
        return new ProductsPage(driver);
    }

    @Step("Click the 'Continue Shopping' button")
    public ProductsPage clickContinueShoppingButton() {
        continueShoppingButton.click();
        logger.info("Clicked the 'Continue Shopping' button");
        return new ProductsPage(driver);
    }

    @Step("Click the 'Checkout' button")
    public CheckoutPage clickCheckoutShoppingButton() {
        checkoutButton.click();
        logger.info("Clicked the 'Checkout' button");
        return new CheckoutPage(driver);
    }

    private List<WebElement> getListOfCartItems() {
        return driver.findElements(By.className("cart_item"));
    }
}

