package tests;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.App;
import data.Product;
import helpers.ActionOnFailure;
import helpers.AllureAttachments;
import helpers.DriverSettings;
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
@ExtendWith({ActionOnFailure.class})
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
        if (WebDriverRunner.driver().hasWebDriverStarted()) {
            AllureAttachments.addPageSource();
            if (Configuration.browser.equals(Browsers.CHROME))
                AllureAttachments.addBrowserConsoleLogs();
        }
    }
}
