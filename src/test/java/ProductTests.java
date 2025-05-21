import com.swag.labs.enums.SortingType;
import com.swag.labs.pages.CartPage;
import com.swag.labs.pages.LoginPage;
import com.swag.labs.pages.ProductsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.swag.labs.config.ConfigReader.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductTests extends BaseTest {

    private static final int ONE_ITEM_IN_THE_CART = 1;
    private static final int TWO_ITEMS_IN_THE_CART = 2;
    private static final int THREE_ITEMS_IN_THE_CART = 3;
    private final List<String> titlesAsc = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
    private final List<String> pricesAsc = Arrays.asList("7.99", "9.99", "15.99", "15.99", "29.99", "49.99");
    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver.get(getBaseUrl());
        productsPage = new LoginPage(driver).login(getStandardUser(), getPassword());
        assertTrue(productsPage.isPageOpened(), "The 'Products' page is opened");
    }

    @Test(testName = "Add products to the cart")
    @Epic("Products")
    @Story("Add to the cart")
    public void addProductsToTheCart() {
        productsPage.addProduct(0);
        assertEquals(productsPage.getNumberOfItemsInTheCart(), ONE_ITEM_IN_THE_CART, "The cart should show the right amount of items");
        productsPage.addProduct(1).addProduct(2);
        assertEquals(productsPage.getNumberOfItemsInTheCart(), THREE_ITEMS_IN_THE_CART, "The cart should show the right amount of items");

        cartPage = productsPage.openShoppingCart();
        assertTrue(cartPage.isPageOpened(), "The 'Cart' page should be opened");
        assertEquals(cartPage.getCartItemsAmount(), THREE_ITEMS_IN_THE_CART, "The cart should show the right amount of items");
        cartPage.clickContinueShoppingButton().logout();
    }

    @Test(testName = "Delete products from the cart")
    @Epic("Products")
    @Story("Delete from the cart")
    public void deleteProductsFromTheCart() {
        productsPage.addProduct(1).addProduct(2);
        assertEquals(productsPage.getNumberOfItemsInTheCart(), TWO_ITEMS_IN_THE_CART, "The cart should show the right amount of items");

        cartPage = productsPage.openShoppingCart();
        assertTrue(cartPage.isPageOpened(), "The 'Cart' page should be opened");
        assertEquals(cartPage.getCartItemsAmount(), TWO_ITEMS_IN_THE_CART, "The cart should show the right amount of items");
        productsPage = cartPage.clickContinueShoppingButton();

        productsPage.deleteProduct(1);
        assertEquals(productsPage.getNumberOfItemsInTheCart(), TWO_ITEMS_IN_THE_CART - 1, "The cart should show the right amount of items");
        cartPage = productsPage.openShoppingCart();
        assertEquals(cartPage.getCartItemsAmount(), TWO_ITEMS_IN_THE_CART - 1, "The cart should show the right amount of items");

        productsPage = cartPage.gotoAlItemsPage();
        productsPage.deleteProduct(2);
        assertEquals(productsPage.getNumberOfItemsInTheCart(), TWO_ITEMS_IN_THE_CART - 2, "The cart should show the right amount of items");
        productsPage.logout();
    }

    @Test(testName = "Verify products sorting")
    @Epic("Products")
    @Story("Sort products")
    public void sortProducts() {
        productsPage.sortProducts(SortingType.NAME_A_Z);
        assertEquals(productsPage.getAllProductsTitle(), titlesAsc, "Titles should be sorted by ASC");

        productsPage.sortProducts(SortingType.PRICE_LOW_TO_HIGH);
        assertEquals(productsPage.getAllProductsPrices(), pricesAsc, "Prices should be sorted by ASC");
    }
}

