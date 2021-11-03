package pages.checkout.components;

import com.codeborne.selenide.SelenideElement;
import dictionaries.CreditCardType;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CreditCard {

    private SelenideElement creditCardType = $("#CreditCardType"),
            cardholderNameInput = $("#CardholderName"),
            cardNumberInput = $("#CardNumber"),
            expireMonth = $("#ExpireMonth"),
            expireYear = $("#ExpireYear"),
            cardCodeInput = $("#CardCode");

    @Step("Filling credit card data")
    public void fillCreditCard(CreditCardType type, String cardHolder,
                               String cardNumber, int month, int year, int cardCode) {
        creditCardType.selectOptionByValue(type.getValue());
        cardholderNameInput.val(cardHolder);
        cardNumberInput.val(cardNumber);
        expireMonth.selectOptionByValue(String.valueOf(month));
        expireYear.selectOptionByValue(String.valueOf(year));
        cardCodeInput.val(String.valueOf(cardCode));
    }
}
