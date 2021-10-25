package tests.api;

import allure.JiraIssue;
import api.ApiSteps;
import config.App;
import data.User;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@Tag("api")
@Story("Registration")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-264")
public class RegisterAPITests {
    private User user = new User();
    private final ApiSteps apiSteps = new ApiSteps();
    private final static String OK_REGISTRATION=
            "Object moved to <a href=\"/registerresult/1\">here</a>";
    private final static String WRONG_REGISTRATION=
            "Object moved to <a href=\"/errorpage.htm?aspxerrorpath=/register\">here</a>";

    @BeforeAll
    public static void initBaseUrl(){
        RestAssured.baseURI = App.config.apiUrl();
    }

    //todo добавить в allure красивые запросы + вложения
    @Test
    @DisplayName("Successful register through API")
    public void registerAPITest(){
        Response getResponse = apiSteps.getRequestForRegistration();
        String postResponse = apiSteps.registerUser(user, apiSteps.getToken(getResponse), apiSteps.getCookie(getResponse));
        Assertions.assertTrue(postResponse.contains(OK_REGISTRATION));
    }

    @Test
    @DisplayName("Unsuccessful register through API because cookie is empty")
    public void registerAPITestCookieIsEmpty(){
        Response getResponse = apiSteps.getRequestForRegistration();
        String postResponse = apiSteps.registerUser(user, apiSteps.getToken(getResponse), "");
        Assertions.assertTrue(postResponse.contains(WRONG_REGISTRATION));
    }

    @Test
    @DisplayName("Unsuccessful register through API because token is empty")
    public void registerAPITestTokenIsEmpty(){
        Response getResponse = apiSteps.getRequestForRegistration();
        String postResponse = apiSteps.registerUser(user, "", apiSteps.getToken(getResponse));
        Assertions.assertTrue(postResponse.contains(WRONG_REGISTRATION));
    }

}
