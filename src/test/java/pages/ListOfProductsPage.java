package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Product;
import io.qameta.allure.Step;
import pages.components.MainHeader;
import pages.components.MenuOfProducts;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;

public class ListOfProductsPage {

    private MainHeader mainHeader = new MainHeader();
    private MenuOfProducts menu = new MenuOfProducts();

    private ElementsCollection products = $$(".product-grid .product-item"),
            productsAddToCard = $$(".product-grid .item-box input[value='Add to cart']");

    @Step("Getting main header")
    public MainHeader getMainHeader() {
        return mainHeader;
    }

    @Step("Getting menu")
    public MenuOfProducts getMenu() {
        return menu;
    }

    @Step("Choosing random product and adding it to shopping cart")
    public Product addRandomProductToCart(){
        SelenideElement element = productsAddToCard.get(new Random().nextInt(productsAddToCard.size()));
        String onclickStr = element.getAttribute("onclick");
        String productId = onclickStr.substring(onclickStr.indexOf("/catalog")+9,
                onclickStr.indexOf("/", onclickStr.indexOf("/catalog")+9));
        SelenideElement chosenProduct = products.find(Condition.attribute("data-productid", productId));
        Product product = new Product(chosenProduct.$(".product-title a").getText(),
                Double.valueOf(chosenProduct.$(".actual-price").getText()));
        element.click();
        return product;
    }
}
