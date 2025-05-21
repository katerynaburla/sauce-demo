import com.swag.labs.components.ErrorMessageComponent;
import com.swag.labs.enums.ErrorMessage;
import com.swag.labs.pages.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.swag.labs.config.ConfigReader.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTests extends BaseTest {

    private static final int ONE_ITEM_IN_THE_CART = 1;
    private static final int THREE_ITEMS_IN_THE_CART = 3;
    private static final String THANK_YOU_FOR_THE_ORDER = "Thank you for your order!";
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage overviewPage;
    private CheckoutCompletePage completePage;

    @BeforeMethod
    public void setUp() {
        driver.get(getBaseUrl());
        productsPage = new LoginPage(driver).login(getStandardUser(), getPassword());
        assertTrue(productsPage.isPageOpened(), "The 'Products' page is opened");
        productsPage.appReset();
    }

    @Test(testName = "'Checkout Information' validation")
    @Epic("Checkout")
    @Story("'Checkout Information' validation")
    public void verifyCheckoutInfoValidation() {
        productsPage.addProduct(5);
        assertEquals(productsPage.getNumberOfItemsInTheCart(), ONE_ITEM_IN_THE_CART, "Product should be added to the cart");

        cartPage = productsPage.openShoppingCart();
        assertTrue(cartPage.isPageOpened(), "The 'Cart' page should be opened");
        assertEquals(cartPage.getCartItemsAmount(), ONE_ITEM_IN_THE_CART, "Product should be present in the cart");

        checkoutPage = cartPage.clickCheckoutShoppingButton();
        assertTrue(checkoutPage.isPageOpened(), "The 'Checkout' page should be opened");

        verifyInputsValidation();
        cartPage = checkoutPage.clickCancelShoppingButton();
        assertTrue(cartPage.isPageOpened(), "The 'Cart' page should be opened");
    }

    @Test(testName = "Checkout with one product")
    @Epic("Checkout")
    @Story("Checkout with one product")
    public void checkoutWithOneProduct() {
        cartPage = productsPage
                .addProduct(5)
                .openShoppingCart();
        assertTrue(cartPage.isPageOpened(), "The 'Cart' page should be opened");
        assertEquals(cartPage.getCartItemsAmount(), ONE_ITEM_IN_THE_CART, "The cart should show the right amount of items");

        cartPage
                .clickCheckoutShoppingButton()
                .setCredentials(getFirstName(), getLastName(), getPostalCode());
        overviewPage = new CheckoutOverviewPage(driver);
        assertTrue(overviewPage.isPageOpened(), "The 'Checkout Overview' page should be opened");
        assertEquals(overviewPage.getCartItemsAmount(), ONE_ITEM_IN_THE_CART, "The 'Checkout Overview' should show the right amount of items");

        completePage = overviewPage.clickFinishShoppingButton();
        assertTrue(completePage.isPageOpened(), "The 'Checkout: Complete!' page should be opened");
        assertEquals(completePage.getCheckoutStatus(), THANK_YOU_FOR_THE_ORDER, "Complete message should be correct");
    }

    @Test(testName = "Checkout with multiple products")
    @Epic("Checkout")
    @Story("Checkout with multiple products")
    public void checkoutWithMultipleProducts() {
        productsPage
                .addProduct(2)
                .addProduct(4)
                .addProduct(5)
                .openShoppingCart()
                .clickCheckoutShoppingButton()
                .setCredentials(getFirstName(), getLastName(), getPostalCode());
        overviewPage = new CheckoutOverviewPage(driver);
        assertEquals(overviewPage.getCartItemsAmount(), THREE_ITEMS_IN_THE_CART, "The 'Checkout Overview' should show the right amount of items");

        completePage = overviewPage.clickFinishShoppingButton();
        assertTrue(completePage.isPageOpened(), "The 'Checkout: Complete!' page should be opened");
        assertEquals(completePage.getCheckoutStatus(), THANK_YOU_FOR_THE_ORDER, "Complete message should be correct");

        productsPage = completePage.clickBackHomeButton();
        assertTrue(productsPage.isPageOpened(), "The 'Products' page should be opened");
    }

    @Test(testName = "Cancel checkout")
    @Epic("Checkout")
    @Story("Cancel checkout")
    public void cancelCheckout() {
        productsPage
                .addProduct(3)
                .openShoppingCart()
                .clickCheckoutShoppingButton()
                .setCredentials(getFirstName(), getLastName(), getPostalCode());
        productsPage = new CheckoutOverviewPage(driver).clickCancelShoppingButton();
        assertTrue(productsPage.isPageOpened(), "'Products' page should be opened");
        assertEquals(productsPage.getNumberOfItemsInTheCart(), ONE_ITEM_IN_THE_CART, "One item should be in the cart");
    }

    private void verifyErrorMessage(ErrorMessage message) {
        ErrorMessageComponent error = new ErrorMessageComponent(driver);
        assertEquals(error.getErrorMessage(), message.getMessage(),
                "Wrong verification message!");
    }

    private void verifyInputsValidation() {
        checkoutPage.setCredentials("", "", "");
        verifyErrorMessage(ErrorMessage.FIRST_NAME_IS_REQUIRED);

        checkoutPage.setCredentials(getFirstName(), "", "");
        verifyErrorMessage(ErrorMessage.LAST_NAME_IS_REQUIRED);

        checkoutPage.setCredentials(getFirstName(), getLastName(), "");
        verifyErrorMessage(ErrorMessage.POSTAL_CODE_IS_REQUIRED);
    }
}

