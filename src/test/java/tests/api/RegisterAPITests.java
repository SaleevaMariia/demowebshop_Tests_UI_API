package tests.api;

import allure.JiraIssue;
import api.ApiSteps;
import config.App;
import data.User;
import helpers.ActionOnFailure;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("api")
@Story("Registration")
@Owner("saleevamo")
@JiraIssue("HOMEWORK-264")
@ExtendWith({ActionOnFailure.class})
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

    @Test
    @DisplayName("Successful register through API")
    public void registerAPITest(){
        Response getResponse = apiSteps.getRequestForRegistration();
        String postResponse = apiSteps.registerUser(user, apiSteps.getToken(getResponse), apiSteps.getCookie(getResponse));
        assertTrue(postResponse.contains(OK_REGISTRATION));
    }

    @Test
    @DisplayName("Unsuccessful register through API because cookie is empty")
    public void registerAPITestCookieIsEmpty(){
        Response getResponse = apiSteps.getRequestForRegistration();
        String postResponse = apiSteps.registerUser(user, apiSteps.getToken(getResponse), "");
        assertTrue(postResponse.contains(WRONG_REGISTRATION));
    }

    @Test
    @DisplayName("Unsuccessful register through API because token is empty")
    public void registerAPITestTokenIsEmpty(){
        Response getResponse = apiSteps.getRequestForRegistration();
        String postResponse = apiSteps.registerUser(user, "", apiSteps.getToken(getResponse));
        assertTrue(postResponse.contains(WRONG_REGISTRATION));
    }
}
