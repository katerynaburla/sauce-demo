package com.swag.labs.components;

import com.swag.labs.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorMessageComponent extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ErrorMessageComponent.class);
    private final WebElement errorMessageContainer = driver.findElement(By.className("error-message-container"));

    public ErrorMessageComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Get the error message")
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageContainer));
        String error = errorMessageContainer.getText();
        logger.info("Error message is: {}", error);
        return error;
    }
}
