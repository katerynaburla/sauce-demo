package com.swag.labs.pages;

import com.swag.labs.enums.MenuItem;
import io.qameta.allure.Step;
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
        WebElement menu = driver.findElement(By.className("bm-menu-wrap"));
        if (menu.getDomAttribute("aria-hidden").equals("true")) {
            burgerMenuButton.click();
        }
        wait.until(ExpectedConditions.visibilityOf(menu));
        WebElement item = driver.findElement(By.id(menuItem.getId()));
        wait.until(ExpectedConditions.visibilityOf(item));
        item.click();
    }

    @Step("App reset")
    public void appReset() {
        this.selectMenuItem(MenuItem.RESET_APP_STATE);
    }
}


