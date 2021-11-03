package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement emailInput = $("#Email"),
            passwordInput = $("#Password"),
            loginBtn = $(".login-button");


    @Step("Logging with email:{email} and password:{password}")
    public ListOfProductsPage login(String email, String password) {
        emailInput.val(email);
        passwordInput.val(password);
        loginBtn.click();
        return new ListOfProductsPage();
    }
}
