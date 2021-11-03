package api;

import data.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

import java.util.List;

import static dictionaries.ApiEndpoint.LOGIN;
import static dictionaries.ApiEndpoint.REGISTER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class ApiSteps {

    @Step("Registering user through POST request to /register")
    public String registerUser(User user, String token, String cookie){
        String body = String.format("%s&register-button=Register&__RequestVerificationToken=%s",
                user.toRegisterPostRequest(),token);
        String response = given()
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .cookie(cookie)
                .body(body)
                .when()
                .post(REGISTER.getPath())
                .then()
                .statusCode(302)
                .extract().response().asString();
        return response;
    }

    @Step("Getting response to GET request /register")
    public Response getRequestForRegistration(){
        Response response = given()
                .get(REGISTER.getPath())
                .then()
                .extract().response();
        return response;
    }

    @Step("Getting token from result of GET request /register")
    public String getToken(Response response){
        XmlPath getXmlResponse = response.htmlPath();
        String xsrfTokenStr = getXmlResponse.getString("**.find { it.@name == '__RequestVerificationToken' }.@value");
        return xsrfTokenStr;
    }

    @Step("Getting cookie from result")
    public String getCookie(Response response){
        List<String> headers = response.getHeaders().getValues("Set-Cookie");
        String cookie = "";
        for (String s: headers) {
            cookie += s.substring(0, s.indexOf(';'));
            cookie += "; ";
        }
        return cookie;
    }

    @Step("Logging user")
    public String loginUser(User user){
        String body = String.format("Email=%s&Password=%s&RememberMe=false", user.getEmail(), user.getPassword());
        Response response = given()
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .body(body)
                .when()
                .post(LOGIN.getPath())
                .then()
                .statusCode(302)
                .extract().response();
        String cookie = getCookie(response);
        return  getAuthCookie(cookie);
    }

    @Step("Getting cookie NOPCOMMERCE.AUTH from all cookies")
    public String getAuthCookie(String cookie){
        int startIndex = cookie.indexOf("NOPCOMMERCE.AUTH")+17;
        return cookie.substring(startIndex, cookie.indexOf(";", startIndex));
    }

    @Step("Adding product to shopping cart with id = {productId}")
    public void addProductToShoppingCart(int productId, Cookie authCookie){
        given()
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .cookie(String.valueOf(authCookie))
                .when()
                .post(String.format("/addproducttocart/catalog/%s/1/1", productId))
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }
}
