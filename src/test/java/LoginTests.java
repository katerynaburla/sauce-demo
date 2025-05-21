import com.swag.labs.enums.ErrorMessage;
import com.swag.labs.pages.LoginPage;
import com.swag.labs.pages.ProductsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.swag.labs.config.ConfigReader.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        driver.get(getBaseUrl());
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "credentials")
    public Object[][] credentials() {
        return new Object[][]{
                {getStandardUser(), getPassword()},
                {getProblemUser(), getPassword()},
                {getErrorUser(), getPassword()},
                {getVisualUser(), getPassword()},
                {getPerformanceGlitchUser(), getPassword()},
        };
    }

    @Test(description = "Verify credentials inputs validation for the 'Locked Out user'")
    @Epic("Login")
    @Story("Login validation")
    public void validationForLockedOutUser() {
        loginPage.setCredentials("", "");
        verifyErrorMessage(ErrorMessage.USERNAME_IS_REQUIRED);

        loginPage.setCredentials(getLockedOutUser(), "");
        verifyErrorMessage(ErrorMessage.PASSWORD_IS_REQUIRED);

        loginPage.setCredentials(getPassword(), getLockedOutUser());
        verifyErrorMessage(ErrorMessage.DO_NOT_MATCH_ANY);

        loginPage.setCredentials(getLockedOutUser(), getPassword());
        verifyErrorMessage(ErrorMessage.LOCKED_OUT_USER);
    }

    @Test(dataProvider = "credentials", description = "Verify credentials inputs validation")
    @Epic("Login")
    @Story("Login validation")
    public void loginValidation(String username, String password) {
        loginPage.setCredentials("", "");
        verifyErrorMessage(ErrorMessage.USERNAME_IS_REQUIRED);

        loginPage.setCredentials(username, "");
        verifyErrorMessage(ErrorMessage.PASSWORD_IS_REQUIRED);

        loginPage.setCredentials(password, username);
        verifyErrorMessage(ErrorMessage.DO_NOT_MATCH_ANY);
    }

    @Test(dataProvider = "credentials", description = "Verify that user is able to log in")
    @Epic("Login")
    @Story("Login")
    public void login(String username, String password) {
        productsPage = loginPage.login(username, password);
        assertTrue(productsPage.isProductsHeaderVisible(), "The 'Products' header should be visible");
    }

    @Test(dataProvider = "credentials", description = "Verify that user is able to log out")
    @Epic("Login")
    @Story("Logout")
    public void logout(String username, String password) {
        productsPage = loginPage.login(username, password);
        loginPage = productsPage.logout();
        assertTrue(loginPage.isLoginPageOpened(), "The 'Login' page should be opened");
    }

    private void verifyErrorMessage(ErrorMessage message) {
        assertTrue(loginPage.isErrorVisible(), "Error message should is visible");
        assertEquals(loginPage.getErrorMessage(), message.getMessage(),
                "Wrong verification message!");
    }
}

