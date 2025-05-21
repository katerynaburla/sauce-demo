package com.swag.labs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final WebElement usernameInput = driver.findElement(By.id("user-name"));
    private final WebElement passwordInput = driver.findElement(By.id("password"));
    private final WebElement loginButton = driver.findElement(By.id("login-button"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check whether the 'Login' page is opened")
    public boolean isPageOpened() {
        boolean isDisplayed = usernameInput.isDisplayed();
        logger.debug("The 'Login' page is opened: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Set username: '{name}' and password: {password}")
    public void setCredentials(String name, String password) {
        logger.info("Set username: {} and password: {}", name, password);
        setValue(usernameInput, name);
        setValue(passwordInput, password);
        loginButton.click();
    }

    @Step("Log In as: '{name}' with password: {password}")
    public ProductsPage login(String name, String password) {
        logger.info("Login as: {} with: {}", name, password);
        this.setCredentials(name, password);
        wait.until(ExpectedConditions.invisibilityOf(loginButton));
        return new ProductsPage(driver);
    }
}
