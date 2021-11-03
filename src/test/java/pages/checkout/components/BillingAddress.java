package pages.checkout.components;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import pages.checkout.CheckoutPage;

import static com.codeborne.selenide.Selenide.$;

public class BillingAddress {

    private SelenideElement selectAddress = $("#billing-address-select"),
            selectCountry = $("#BillingNewAddress_CountryId"),
            cityInput = $("#BillingNewAddress_City"),
            addressInput = $("#BillingNewAddress_Address1"),
            zipCodeInput = $("#BillingNewAddress_ZipPostalCode"),
            phoneNumberInput = $("#BillingNewAddress_PhoneNumber"),
            continueBtn = $("#billing-buttons-container .new-address-next-step-button");

    @Step("Filling new Address")
    public CheckoutPage createNewAddress() {
        Faker faker = new Faker();
        if (selectAddress.exists()) {
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
