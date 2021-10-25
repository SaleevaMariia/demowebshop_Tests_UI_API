package tests.ui;

import allure.JiraIssue;
import dictionaries.Menu;
import helpers.CleanCart;
import helpers.WithLogin;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;

@Tag("ui")
@Story("ShoppingCart")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-265")
@ResourceLock("CommonShoppingCart")
public class ShoppingCartUITests extends TestBase {

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Adding one product to shopping cart")
    public void addOneProductToShoppingCardUITest(){
        open("");
        addProductToShoppingCard(Menu.BOOKS);
        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();

        shoppingCartPage.checkProductsInShoppingCart(productList);
        shoppingCartPage.checkTotalPrice(getTotalSum(productList));
    }

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Adding two different product to shopping cart")
    public void addTwoProductsToShoppingCardUITest(){
        open("");

        addProductToShoppingCard(Menu.BOOKS);
        addProductToShoppingCard(Menu.DIGITAL_DOWNLOADS);

        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();

        shoppingCartPage.checkProductsInShoppingCart(productList);
        shoppingCartPage.checkTotalPrice(getTotalSum(productList));
    }

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Deleting product from shopping cart")
    public void deletingProductShoppingCardUITest(){
        open("");
        addProductToShoppingCard(Menu.BOOKS);
        addProductToShoppingCard(Menu.DIGITAL_DOWNLOADS);

        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();

        shoppingCartPage.deleteProductFromShoppingCart(productList.remove(0));

        shoppingCartPage.checkProductsInShoppingCart(productList);
        shoppingCartPage.checkTotalPrice(getTotalSum(productList));
    }

}
