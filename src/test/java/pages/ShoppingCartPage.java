package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.checkout.CheckoutPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static helpers.Utils.scrollToElementAndClick;

public class ShoppingCartPage {

    private ElementsCollection productsInCart = $$(".cart-item-row");
    private SelenideElement subTotalSum = $(By.xpath("//span[contains(text(), 'Sub-Total')]/../following-sibling::td//span[@class='product-price']"));
    private SelenideElement updateCartBtn = $("input[name='updatecart']");
    private SelenideElement continueShoppingBtn = $("input[name='continueshopping']");
    private SelenideElement termsOfService = $("input#termsofservice");
    private SelenideElement checkOutBtn = $("button#checkout");

    @Step("Checking the contents of the basket")
    public boolean checkProductsInShoppingCart(List<Product> products){
        productsInCart.shouldHaveSize(products.size());
        for (SelenideElement p: productsInCart) {
            if(!products.contains(
                    new Product(p.$(".product-name").getText(),
                            Double.valueOf(p.$(".product-unit-price").getText()))))
                return false;
        }
        return true;
    }

    @Step("Checking total price")
    public void checkTotalPrice(double expectedTotalPrice){
        subTotalSum.shouldHave(Condition.text(String.valueOf(expectedTotalPrice)));
    }

    @Step("Cleaning shopping cart")
    public void cleaningShoppingCart(){
        for (SelenideElement p: productsInCart) {
            p.$("input[name='removefromcart']").click();
        }
        updateCartBtn.click();
    }

    @Step("Deleting product from shopping cart")
    public void deleteProductFromShoppingCart(Product product){
        for (SelenideElement p: productsInCart) {
            if(p.$(".product a").getText().equals(product.getName())){
                p.$("input[name='removefromcart']").click();
                break;
            }
        }
        updateCartBtn.click();
    }

    @Step("Checking out shopping cart")
    public CheckoutPage checkOut(){
        actions().moveToElement(termsOfService).click(termsOfService).perform();
        scrollToElementAndClick(checkOutBtn);
        return new CheckoutPage();
    }
}
