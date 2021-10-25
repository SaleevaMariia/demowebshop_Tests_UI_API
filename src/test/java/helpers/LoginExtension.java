package helpers;

import api.ApiSteps;
import com.codeborne.selenide.WebDriverRunner;
import config.App;
import data.User;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        String cookieAuth =
                new ApiSteps().loginUser(new User(App.config.userEmail(), App.config.userPassword()));
        open("");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookieAuth));
    }
}