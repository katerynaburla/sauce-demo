import com.swag.labs.enums.ErrorMessage;
import com.swag.labs.pages.LoginPage;
import com.swag.labs.pages.ProductsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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

    @Test(testName = "Verifying login validation for the 'Locked Out user'")
    @Epic("Login")
    @Story("Login validation")
    @Parameters({"Login", "Password"})
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

    @Test(dataProvider = "credentials", description = "Verifying login validation")
    @Epic("Login")
    @Story("Login validation")
    @Parameters({"Login", "Password"})
    public void loginValidation(String username, String password) {
        loginPage.setCredentials("", "");
        verifyErrorMessage(ErrorMessage.USERNAME_IS_REQUIRED);

        loginPage.setCredentials(username, "");
        verifyErrorMessage(ErrorMessage.PASSWORD_IS_REQUIRED);

        loginPage.setCredentials(password, username);
        verifyErrorMessage(ErrorMessage.DO_NOT_MATCH_ANY);
    }

    @Test(dataProvider = "credentials", testName = "Verifying successful log in")
    @Epic("Login")
    @Story("Login")
    @Parameters({"Login", "Password"})
    public void login(String username, String password) {
        productsPage = loginPage.login(username, password);
        assertTrue(productsPage.isProductsHeaderVisible(), "The 'Products' header should be visible");
    }

    @Test(dataProvider = "credentials", testName = "Verifying successful log out")
    @Epic("Login")
    @Story("Logout")
    @Parameters({"Login", "Password"})
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

