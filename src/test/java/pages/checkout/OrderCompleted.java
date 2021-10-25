package pages.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class OrderCompleted {

    private SelenideElement result = $(".order-completed strong");

    @Step("Checking success order")
    public void checkResult(){
        result.shouldHave(Condition.text("Your order has been successfully processed!"));
    }

}
