package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.ShoppingCartPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MainHeader {

    private SelenideElement self = $(".header-links"),
            account = self.$(".account"),
            logOut = self.$(".ico-logout"),
            cart = self.$(".ico-cart"),
            cartQty = cart.$(".cart-qty"),
            logIn = self.$(".ico-login"),
            register = self.$(".ico-register");

    @Step("Checking that user with email:{email} logged in")
    public boolean isUserLogIn(String email) {
        if (account.has(visible) && account.has(text(email))
                && logIn.has(not(visible)))
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
