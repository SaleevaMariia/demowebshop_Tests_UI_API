package tests.api;

import allure.JiraIssue;
import api.ApiSteps;
import com.codeborne.selenide.WebDriverRunner;
import config.App;
import data.User;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ListOfProductsPage;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;

@Tag("api")
@Story("Login")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-263")
public class LoginAPITests extends TestBase {

    private User user = new User(App.config.userEmail(), App.config.userPassword());
    private final ApiSteps apiSteps = new ApiSteps();
    private ListOfProductsPage listOfProducts = new ListOfProductsPage();


    @Test
    @DisplayName("Successful login through API")
    public void loginAPITest(){
        String cookie = apiSteps.loginUser(user);
        open("");
        Cookie auth = new Cookie("NOPCOMMERCE.AUTH", cookie);
        WebDriverRunner.getWebDriver().manage().addCookie(auth);
        open("");
        Assertions.assertTrue(listOfProducts.getMainHeader().isUserLogIn(user.getEmail()));
    }
}
