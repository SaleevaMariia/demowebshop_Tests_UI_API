package tests.api;

import allure.JiraIssue;
import api.ApiSteps;
import com.codeborne.selenide.WebDriverRunner;
import data.Product;
import helpers.CleanCart;
import helpers.WithLogin;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.openqa.selenium.Cookie;
import pages.ShoppingCartPage;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

@Tag("api")
@Story("ShoppingCart")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-265")
@ResourceLock("CommonShoppingCart")
public class ShoppingCartAPITests extends TestBase {

    //todo вынести продукты в property + в продукт добавить поле id
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    List<Product> productList = new ArrayList<>();
    private final ApiSteps apiSteps = new ApiSteps();
    private Product book = new Product("Computing and Internet", 10.00);
    private Product jewelry = new Product("Black & White Diamond Heart", 130.00);

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Adding one book to shopping cart")
    public void addOneProductToShoppingCardAPITest(){
        apiSteps.addProductToShoppingCart(13,
                WebDriverRunner.getWebDriver().manage().getCookieNamed("NOPCOMMERCE.AUTH"));
        productList.add(book);
        open("/cart");
        shoppingCartPage.checkProductsInShoppingCart(productList);
        shoppingCartPage.checkTotalPrice(getTotalSum(productList));
    }

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Adding two products to shopping cart")
    public void addOTwoProductsToShoppingCardAPITest(){
        Cookie auth =  WebDriverRunner.getWebDriver().manage().getCookieNamed("NOPCOMMERCE.AUTH");
        apiSteps.addProductToShoppingCart(13, auth);
        apiSteps.addProductToShoppingCart(14, auth);
        productList.add(book);
        productList.add(jewelry);
        open("/cart");
        shoppingCartPage.checkProductsInShoppingCart(productList);
        shoppingCartPage.checkTotalPrice(getTotalSum(productList));
    }

}
