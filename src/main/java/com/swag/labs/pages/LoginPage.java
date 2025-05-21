package com.swag.labs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final WebElement usernameInput = driver.findElement(By.id("user-name"));
    private final WebElement passwordInput = driver.findElement(By.id("password"));
    private final WebElement loginButton = driver.findElement(By.id("login-button"));
    private final WebElement errorMessageContainer = driver.findElement(By.className("error-message-container"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageOpened() {
        logger.info("Check whether the 'Login' page is opened");
        boolean isDisplayed = usernameInput.isDisplayed();
        logger.debug("The 'Login' page is opened: {}", isDisplayed);
        return isDisplayed;
    }

    public boolean isErrorVisible() {
        logger.info("Check whether the error message is displayed");
        boolean isDisplayed = errorMessageContainer.isDisplayed();
        logger.debug("Error visible: {}", isDisplayed);
        return isDisplayed;
    }

    public String getErrorMessage() {
        logger.info("Get the error");
        String error = errorMessageContainer.getText();
        logger.debug("Error message: {}", error);
        return error;
    }

    public void setCredentials(String name, String password) {
        logger.info("Set username: {} and password: {}", name, password);
        setValue(usernameInput, name);
        setValue(passwordInput, password);
        loginButton.click();
    }

    public ProductsPage login(String name, String password) {
        logger.info("Login as: {} with: {}", name, password);
        this.setCredentials(name, password);
        wait.until(ExpectedConditions.invisibilityOf(loginButton));
        return new ProductsPage(driver);
    }

    private void setValue(WebElement input, String value) {
        if (!(requireNonNull(input.getDomAttribute("value"))).isEmpty()) {
            input.clear();
        }
        actions.sendKeys(input, value).perform();
    }
}
