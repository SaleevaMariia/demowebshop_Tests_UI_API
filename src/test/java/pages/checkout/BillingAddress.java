package pages.checkout;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class BillingAddress {

    private SelenideElement selectAddress = $("#billing-address-select");
    private SelenideElement selectCountry = $("#BillingNewAddress_CountryId");
    private SelenideElement cityInput = $("#BillingNewAddress_City");
    private SelenideElement addressInput = $("#BillingNewAddress_Address1");
    private SelenideElement zipCodeInput = $("#BillingNewAddress_ZipPostalCode");
    private SelenideElement phoneNumberInput = $("#BillingNewAddress_PhoneNumber");
    private SelenideElement continueBtn = $("#billing-buttons-container .new-address-next-step-button");



    @Step("Filling new Address")
    public CheckoutPage createNewAddress(){
        Faker faker = new Faker();
        if(selectAddress.exists()){
            selectAddress.selectOptionContainingText("New Address");
        }
        selectCountry.selectOptionByValue("2");
        cityInput.val(faker.address().city());
        addressInput.val(faker.address().streetAddress());
        zipCodeInput.val(faker.address().zipCode());
        phoneNumberInput.val(faker.phoneNumber().cellPhone());
        continueBtn.click();
        return new CheckoutPage();
    }
}
