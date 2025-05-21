package com.swag.labs.pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    protected final WebElement headerTitle = driver.findElement(By.className("title"));
    private final WebElement burgerMenuButton = driver.findElement(By.className("bm-burger-button"));

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectMenuItem(MenuItem menuItem) {
        burgerMenuButton.click();
        WebElement menu = driver.findElement(By.className("bm-menu"));
        wait.until(ExpectedConditions.visibilityOf(menu));
        WebElement item = driver.findElement(By.id(menuItem.getId()));
        wait.until(ExpectedConditions.visibilityOf(item));
        item.click();
    }
}

@Getter
@AllArgsConstructor
enum MenuItem {
    LOGOUT("Logout", "logout_sidebar_link"),
    ALL_ITEMS("All Items", "inventory_sidebar_link");

    private final String message;
    private final String id;
}