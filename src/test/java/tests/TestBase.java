package tests;

import com.codeborne.selenide.Selenide;
import config.App;
import config.Environment;
import data.Product;
import dictionaries.Menu;
import helpers.AllureAttachments;
import helpers.DriverSettings;
import helpers.DriverUtils;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ListOfProductsPage;
import pages.ShoppingCartPage;

import java.util.ArrayList;
import java.util.List;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    protected ListOfProductsPage listOfProductsPage = new ListOfProductsPage();
    protected ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    protected List<Product> productList = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        DriverSettings.configure();
        RestAssured.baseURI = App.config.apiUrl();
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        //todo добавлять вложения только для упавших тестов + скрины в момент падения
        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Environment.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }

    protected double getTotalSum(List<Product> productList){
        double sum = 0;
        for (Product p: productList) {
            sum += p.getPrice();
        }
        return sum;
    }

    protected void addProductToShoppingCard(Menu menu){
        listOfProductsPage.getMenu().navigateTo(menu);
        productList.add(listOfProductsPage.addRandomProductToCart());
    }
}
