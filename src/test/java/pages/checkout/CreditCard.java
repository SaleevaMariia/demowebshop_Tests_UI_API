package pages.checkout;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dictionaries.CreditCardType;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditCard {

    private SelenideElement creditCardType = $("#CreditCardType");
    private SelenideElement cardholderNameInput = $("#CardholderName");
    private SelenideElement cardNumberInput = $("#CardNumber");
    private SelenideElement expireMonth = $("#ExpireMonth");
    private SelenideElement expireYear = $("#ExpireYear");
    private SelenideElement cardCodeInput = $("#CardCode");

    @Step("Filling credit card data")
    public void fillCreditCard(CreditCardType type, String cardHolder,
                                       String cardNumber, int month, int year, int cardCode){
        creditCardType.selectOptionByValue(type.getValue());
        cardholderNameInput.val(cardHolder);
        cardNumberInput.val(cardNumber);
        expireMonth.selectOptionByValue(String.valueOf(month));
        expireYear.selectOptionByValue(String.valueOf(year));
        cardCodeInput.val(String.valueOf(cardCode));
    }

}
