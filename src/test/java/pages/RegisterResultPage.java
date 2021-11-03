package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class RegisterResultPage {

    private SelenideElement registerResultTxt = $(".result"),
            continueBtn = $("input[value='Continue']");

    @Step("Checking registration result")
    public void checkRegistrationResult() {
        registerResultTxt.shouldHave(Condition.text("Your registration completed"));
    }
}
