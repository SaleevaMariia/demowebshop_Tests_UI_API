package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegisterPage {

    private ElementsCollection genderBtns = $$(".gender label");
    private SelenideElement firstNameInput = $("#FirstName");
    private SelenideElement lastNameInput = $("#LastName");
    private SelenideElement emailInput = $("#Email");
    private SelenideElement passwordInput = $("#Password");
    private SelenideElement confirmPasswordInput = $("#ConfirmPassword");
    private SelenideElement registerBtn = $("#register-button");
    private SelenideElement validationErrConfirmPassword = $("span[for='ConfirmPassword']");

    @Step("Filling registration form")
    public RegisterResultPage register(User user){
        genderBtns.find(Condition.text(user.getGender().getValue())).click();
        firstNameInput.val(user.getFirstName());
        lastNameInput.val(user.getLastName());
        emailInput.val(user.getEmail());
        passwordInput.val(user.getPassword());
        confirmPasswordInput.val(user.getConfirmPassword());
        registerBtn.click();
        return new RegisterResultPage();
    }

    @Step("Checking validation error because password != confirm password")
    public void checkValidationErrConfirmPassword(){
        validationErrConfirmPassword.shouldHave(Condition.text("The password and confirmation password do not match"));
    }

}
