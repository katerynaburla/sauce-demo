package com.swag.labs.pages;

import com.swag.labs.components.ProductItemComponent;
import com.swag.labs.enums.MenuItem;
import com.swag.labs.enums.SortingType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductsPage extends HomePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductsPage.class);
    private final WebElement shoppingCartButton = driver.findElement(By.className("shopping_cart_link"));
    private final WebElement sortingSelect = driver.findElement(By.className("product_sort_container"));

    public ProductsPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(this.shoppingCartButton));
    }

    @Step("Check whether the 'Products' page is opened")
    public boolean isProductsHeaderVisible() {
        boolean isDisplayed = this.headerTitle.getText().equalsIgnoreCase("Products");
        logger.info("The 'Products' page is opened: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Add product #{productNumber} to the cart")
    public ProductsPage addProduct(int productNumber) {
        ProductItemComponent product = new ProductItemComponent(driver, getListOfProducts().get(productNumber));
        product.clickAddToCartButton();
        logger.info("Added product #{} to the cart", productNumber);
        return this;
    }

    @Step("Delete product #{productNumber} from the cart")
    public void deleteProduct(int productNumber) {
        ProductItemComponent product = new ProductItemComponent(driver, getListOfProducts().get(productNumber));
        product.clickRemoveButton();
        logger.info("Deleted product #{} from the cart", productNumber);
    }

    @Step("Get products amount in the cart")
    public int getNumberOfItemsInTheCart() {
        String text = shoppingCartButton.getText();
        if (text.isEmpty()) {
            text = "0";
        }
        int numberOfItemsInTheCart = Integer.parseInt(text);
        logger.info("There are {} items in the cart", numberOfItemsInTheCart);
        return numberOfItemsInTheCart;
    }

    @Step("Sort products")
    public void sortProducts(SortingType type) {
        Select select = new Select(sortingSelect);
        select.selectByVisibleText(type.getType());
        logger.info("Selected sorting by: {}", type.getType());
    }

    @Step("Get titles of all products")
    public List<String> getAllProductsTitle() {
        List<WebElement> products = getListOfProducts();
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        List<String> titles = products.stream()
                .map(p ->
                        new ProductItemComponent(driver, products.get(products.indexOf(p))).getTitle())
                .toList();
        logger.info("Sorted items list: {}", titles);
        return titles;
    }

    @Step("Get prices of all products")
    public List<String> getAllProductsPrices() {
        List<WebElement> products = getListOfProducts();
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        List<String> prices = products.stream()
                .map(p ->
                        new ProductItemComponent(driver, products.get(products.indexOf(p))).getPrice())
                .map(p -> p.replace("$", ""))
                .toList();
        logger.info("Sorted items list: {}", prices);
        return prices;
    }

    @Step("Logout")
    public LoginPage logout() {
        this.selectMenuItem(MenuItem.RESET_APP_STATE);
        this.selectMenuItem(MenuItem.LOGOUT);
        logger.info("Click the {} button", MenuItem.LOGOUT.getMessage());
        return new LoginPage(driver);
    }

    private List<WebElement> getListOfProducts() {
        return driver.findElements(By.className("inventory_item"));
    }
}

