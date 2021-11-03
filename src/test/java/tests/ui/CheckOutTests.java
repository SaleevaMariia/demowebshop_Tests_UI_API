package tests.ui;

import allure.JiraIssue;
import dictionaries.CreditCardType;
import dictionaries.Menu;
import dictionaries.PaymentMethods;
import dictionaries.ShippingMethods;
import helpers.CleanCart;
import helpers.WithLogin;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import pages.checkout.CheckoutPage;
import pages.checkout.OrderCompleted;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static helpers.Utils.addProductToShoppingCard;
import static helpers.Utils.getTotalSum;

@Tag("ui")
@Story("Checkout")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-266")
@ResourceLock("CommonShoppingCart")
public class CheckOutTests extends TestBase {

    private CheckoutPage checkoutPage = new CheckoutPage();
    private OrderCompleted orderCompleted = new OrderCompleted();

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Checkout order: get product in store + pay by cash on delivery")
    public void getProductInStorePayByCashOnDeliveryUITest(){
        open("");
        addProductToShoppingCard(Menu.BOOKS, listOfProductsPage, productList);
        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();
        checkoutPage = shoppingCartPage.checkOut();
        checkoutPage
                .fillBillingAddress()
                .chooseShippingAddress(true)
                .choosingPaymentMethod(PaymentMethods.CASH_OD_DELIVERY)
                .clickContinueAfterPaymentInformation();

        checkoutPage.checkTotalSum(
                getTotalSum(productList)
                        + PaymentMethods.CASH_OD_DELIVERY.getPrice()
        );

        orderCompleted = checkoutPage.clickConfirm();
        orderCompleted.checkResult();
    }

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Checkout order: product don't need delivery + pay by card")
    public void getProductNoDeliveryPayByCardUITest(){
        open("");
        addProductToShoppingCard(Menu.DIGITAL_DOWNLOADS, listOfProductsPage, productList);
        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();
        checkoutPage = shoppingCartPage.checkOut();
        checkoutPage
                .fillBillingAddress()
                .choosingPaymentMethod(PaymentMethods.CREDIT_CARD)
                .fillCreditCard(
                        CreditCardType.VISA,
                        "TEST",
                        "4111111111111111",
                        12, 2025,
                        123);

        checkoutPage.checkTotalSum(
                getTotalSum(productList)
                        + PaymentMethods.CREDIT_CARD.getPrice()
        );

        orderCompleted = checkoutPage.clickConfirm();
        orderCompleted.checkResult();
    }

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Checkout order: shipping method is ground + pay by check")
    public void getProductUseGroundShippingMethodPayByCheckUITest() {
        open("");
        addProductToShoppingCard(Menu.BOOKS, listOfProductsPage, productList);
        addProductToShoppingCard(Menu.BOOKS, listOfProductsPage, productList);
        addProductToShoppingCard(Menu.DIGITAL_DOWNLOADS, listOfProductsPage, productList);
        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();
        checkoutPage = shoppingCartPage.checkOut();
        checkoutPage
                .fillBillingAddress()
                .chooseShippingAddress(false)
                .choosingShippingMethod(ShippingMethods.GROUND)
                .choosingPaymentMethod(PaymentMethods.CHECK)
                .clickContinueAfterPaymentInformation();

        checkoutPage.checkTotalSum(
                getTotalSum(productList)
                        + ShippingMethods.GROUND.getPrice()
                        + PaymentMethods.CHECK.getPrice()
        );

        orderCompleted = checkoutPage.clickConfirm();
        orderCompleted.checkResult();
    }

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Checkout order: shipping method is next day air+ pay by purchase")
    public void getProductUseNextDayAirShippingMethodPayByPurchaseUITest() {
        open("");
        addProductToShoppingCard(Menu.BOOKS, listOfProductsPage, productList);
        addProductToShoppingCard(Menu.BOOKS, listOfProductsPage, productList);
        addProductToShoppingCard(Menu.DIGITAL_DOWNLOADS, listOfProductsPage, productList);
        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();
        checkoutPage = shoppingCartPage.checkOut();
        checkoutPage
                .fillBillingAddress()
                .chooseShippingAddress(false)
                .choosingShippingMethod(ShippingMethods.NEXT_DAY_AIR)
                .choosingPaymentMethod(PaymentMethods.PURCHASE_ORDER)
                .fillPurchaseOrderNumber();

        checkoutPage.checkTotalSum(
                getTotalSum(productList)
                        + ShippingMethods.NEXT_DAY_AIR.getPrice()
                        + PaymentMethods.PURCHASE_ORDER.getPrice()
        );

        orderCompleted = checkoutPage.clickConfirm();
        orderCompleted.checkResult();
    }

    @Test
    @WithLogin
    @CleanCart
    @DisplayName("Checkout order: shipping method is second day air + pay by cash on delivery")
    public void getProductUseSecondDayAirShippingMethodPayByCashUITest() {
        open("");
        addProductToShoppingCard(Menu.BOOKS, listOfProductsPage, productList);
        addProductToShoppingCard(Menu.BOOKS, listOfProductsPage, productList);
        addProductToShoppingCard(Menu.DIGITAL_DOWNLOADS, listOfProductsPage, productList);
        shoppingCartPage = listOfProductsPage.getMainHeader().goToShoppingCart();
        checkoutPage = shoppingCartPage.checkOut();
        checkoutPage
                .fillBillingAddress()
                .chooseShippingAddress(false)
                .choosingShippingMethod(ShippingMethods.SECOND_DAY_AIR)
                .choosingPaymentMethod(PaymentMethods.CASH_OD_DELIVERY)
                .clickContinueAfterPaymentInformation();

        checkoutPage.checkTotalSum(
                getTotalSum(productList)
                        + ShippingMethods.SECOND_DAY_AIR.getPrice()
                        + PaymentMethods.CASH_OD_DELIVERY.getPrice()
        );

        orderCompleted = checkoutPage.clickConfirm();
        orderCompleted.checkResult();
    }
}
