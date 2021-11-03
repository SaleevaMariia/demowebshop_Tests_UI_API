package pages.checkout;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import dictionaries.CreditCardType;
import dictionaries.PaymentMethods;
import dictionaries.ShippingMethods;
import io.qameta.allure.Step;
import pages.checkout.components.BillingAddress;
import pages.checkout.components.CreditCard;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage {

    private BillingAddress billingAddress = new BillingAddress();
    private CreditCard creditCard = new CreditCard();

    private SelenideElement shippingContinueBtn = $("#shipping-buttons-container .new-address-next-step-button"),
            pickUpInStoreBtn = $("#PickUpInStore"),
            shippingMethodContinueBtn = $("#shipping-method-buttons-container input"),
            paymentMethodContinueBtn = $("#payment-method-buttons-container input"),
            purchaseOrderNumberInput = $("#PurchaseOrderNumber"),
            paymentInformationContinueBtn = $("#payment-info-buttons-container input"),
            confirmBtn = $("input[value='Confirm']"),
            totalSum = $(".order-total"),
            shippingAddress = $("#shipping-address-select");

    private ElementsCollection shippingMethods = $$(".shipping-method input"),
            shippingMethodsCost = $$(".shipping-method label"),
            paymentMethods = $$(".payment-method input");

    @Step("Filling billing address")
    public CheckoutPage fillBillingAddress() {
        return billingAddress.createNewAddress();
    }

    @Step("Filling credit card data and click continue")
    public CheckoutPage fillCreditCard(CreditCardType type, String cardHolder,
                                       String cardNumber, int month, int year, int cardCode) {
        creditCard.fillCreditCard(type, cardHolder, cardNumber, month, year, cardCode);
        paymentInformationContinueBtn.click();
        return new CheckoutPage();
    }

    @Step("Choosing shipping address. Pick up in store = {isInStore}")
    public CheckoutPage chooseShippingAddress(boolean isInStore){
        if(isInStore){
            pickUpInStoreBtn.click();
        }else {
            shippingAddress.selectOptionContainingText("Canada");
        }
        shippingContinueBtn.click();
        return new CheckoutPage();
    }

    @Step("Choosing shipping method")
    public CheckoutPage choosingShippingMethod(ShippingMethods method) {
        shippingMethods.find(Condition.attribute("id", "shippingoption_" + method.getId()))
                .click();
        String cost = shippingMethodsCost.find(Condition.attribute("for", "shippingoption_" + method.getId()))
                .getText();
        method.setPrice(Double.valueOf(cost.substring(cost.indexOf("(") + 1, cost.indexOf(")"))));
        shippingMethodContinueBtn.click();
        return new CheckoutPage();
    }

    @Step("Choosing payment method")
    public CheckoutPage choosingPaymentMethod(PaymentMethods method){
        paymentMethods.find(Condition.attribute("id", "paymentmethod_" + method.getId()))
                .click();
        paymentMethodContinueBtn.click();
        return new CheckoutPage();
    }

    @Step("Filling purchase order number")
    public CheckoutPage fillPurchaseOrderNumber(){
        Faker faker = new Faker();
        purchaseOrderNumberInput.val(faker.number().digit());
        paymentInformationContinueBtn.click();
        return new CheckoutPage();
    }

    @Step("Clicking continue after seeing payment information")
    public CheckoutPage clickContinueAfterPaymentInformation(){
        paymentInformationContinueBtn.click();
        return new CheckoutPage();
    }

    @Step("Confirming order")
    public OrderCompleted clickConfirm(){
        confirmBtn.click();
        return new OrderCompleted();
    }

    @Step("Checking total sum")
    public void checkTotalSum(double sum){
        totalSum.shouldHave(Condition.text(String.valueOf(sum)));
    }
}
