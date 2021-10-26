package tests.ui;

import allure.JiraIssue;
import config.App;
import data.User;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ListOfProductsPage;
import pages.LoginPage;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static dictionaries.UIEndpoint.LOGIN;

@Tag("ui")
@Story("Login")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-263")
public class LoginUITests extends TestBase {

    private LoginPage loginPage = new LoginPage();
    private User user = new User(App.config.userEmail(), App.config.userPassword());

    @Test
    @DisplayName("Successful login through UI")
    public void registerUITest(){
        open(LOGIN.getPath());
        ListOfProductsPage listOfProducts = loginPage.login(user.getEmail(), user.getPassword());
        Assertions.assertTrue(listOfProducts.getMainHeader().isUserLogIn(user.getEmail()));
    }

    @Test
    @DisplayName("Unsuccessful login through UI - incorrect password")
    public void registerUIWrongPasswordTest(){
        open(LOGIN.getPath());
        ListOfProductsPage listOfProducts = loginPage.login(user.getEmail(), user.getPassword()+"111");
        Assertions.assertFalse(listOfProducts.getMainHeader().isUserLogIn(user.getEmail()));
    }




}
