package com.swag.labs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductsPage extends HomePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductsPage.class);

    public ProductsPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(this.headerTitle));
    }

    public boolean isProductsHeaderVisible() {
        logger.info("Check whether the 'Products' page is opened");
        boolean isDisplayed = this.headerTitle.getText().equalsIgnoreCase("Products");
        logger.debug("'Products' title is visible: {}", isDisplayed);
        return isDisplayed;
    }

    public LoginPage goToAllItems() {
        logger.info("Open 'All Items' page");
        this.selectMenuItem(MenuItem.ALL_ITEMS);
        logger.debug("Click the {} button", MenuItem.ALL_ITEMS.getMessage());
        return new LoginPage(driver);
    }

    public LoginPage logout() {
        logger.info("Logout");
        this.selectMenuItem(MenuItem.LOGOUT);
        logger.debug("Click the {} button", MenuItem.LOGOUT.getMessage());
        return new LoginPage(driver);
    }
}
