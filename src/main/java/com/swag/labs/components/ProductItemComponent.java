package com.swag.labs.components;

import com.swag.labs.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductItemComponent extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductItemComponent.class);
    private final WebElement productPrice;
    private final WebElement productName;
    private final WebElement product;

    public ProductItemComponent(WebDriver driver, WebElement product) {
        super(driver);
        this.product = product;
        productName = product.findElement(By.cssSelector("div.inventory_item_name "));
        productPrice = product.findElement(By.cssSelector("div.inventory_item_price"));
    }

    @Step("Click the 'Add to cart' button")
    public void clickAddToCartButton() {
        logger.info("Clicked the 'Add to cart' button for : {}", productName.getText());
        WebElement addToCartButton = product.findElement(By.cssSelector("[id^=\"add\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

    @Step("Click the 'Remove' button")
    public void clickRemoveButton() {
        logger.info("Clicked the 'Remove' button for : {}", productName.getText());
        WebElement removeButton = product.findElement(By.cssSelector("[id^=\"remove\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeButton.click();
    }

    @Step("Get product's title")
    public String getTitle() {
        String title = productName.getText();
        logger.info("Product's title : {}", title);
        return title;
    }

    @Step("Get product's price")
    public String getPrice() {
        String price = productPrice.getText();
        logger.info("Product's price : {}", price);
        return price;
    }
}
