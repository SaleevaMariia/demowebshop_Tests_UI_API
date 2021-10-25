package helpers;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.ListOfProductsPage;
import pages.ShoppingCartPage;

import static com.codeborne.selenide.Selenide.open;

public class CartExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        open("");
        ListOfProductsPage listOfProductsPage = new ListOfProductsPage();
        if(!listOfProductsPage.getMainHeader().isZeroProductsInShoppingCart()) {
            ShoppingCartPage shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();
            shoppingCartPage.cleaningShoppingCart();
        }
    }
}
