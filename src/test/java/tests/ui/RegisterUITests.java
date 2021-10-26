package tests.ui;

import allure.JiraIssue;
import data.User;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;
import pages.RegisterResultPage;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static dictionaries.UIEndpoint.REGISTER;

@Tag("ui")
@Story("Registration")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-264")
public class RegisterUITests extends TestBase {

    private RegisterPage registerPage = new RegisterPage();
    private User user = new User();

    @Test
    @DisplayName("Successful register through UI")
    public void registerUITest(){
        open(REGISTER.getPath());
        RegisterResultPage resultPage = registerPage.register(user);
        resultPage.checkRegistrationResult();
    }

    @Test
    @DisplayName("Unsuccessful register through UI (password != confirm password)")
    public void registerUITestWithWrongConfirmPassword(){
        open(REGISTER.getPath());
        user.setConfirmPassword(user.getPassword()+"111");
        registerPage.register(user);
        registerPage.checkValidationErrConfirmPassword();
    }
}
