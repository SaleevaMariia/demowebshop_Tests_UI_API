package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.ShoppingCartPage;

import static com.codeborne.selenide.Selenide.$;

public class MainHeader {

    private SelenideElement self = $(".header-links");
    private SelenideElement account = self.$(".account");
    private SelenideElement logOut = self.$(".ico-logout");
    private SelenideElement cart = self.$(".ico-cart");
    private SelenideElement cartQty = cart.$(".cart-qty");
    private SelenideElement logIn = self.$(".ico-login");
    private SelenideElement register = self.$(".ico-register");

    @Step("Checking that user with email:{email} logged in")
    public boolean isUserLogIn(String email){
        if (account.has(Condition.exist) && account.has(Condition.text(email))
                && logIn.has(Condition.not(Condition.exist)))
            return true;
        return false;
    }

    @Step("Going to shopping cart")
    public ShoppingCartPage goToShoppingCart(){
        cart.click();
        return new ShoppingCartPage();
    }

    public boolean isZeroProductsInShoppingCart(){
        if(cartQty.getText().equals("(0)"))
            return true;
        return false;
    }



}
