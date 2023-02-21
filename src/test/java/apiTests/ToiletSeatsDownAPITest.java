package apiTests;

import commonutils.apiUtils.UtilsApiClass;
import config.ConfigReader;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ToiletSeatsDownAPITest extends UtilsApiClass {
    ConfigReader configReader = new ConfigReader();
    String toiletSeatURI = configReader.getProperty("base_api_url") + configReader.getProperty("toiletseat_path");
    SoftAssertions softAssertions = new SoftAssertions();

    @Test(priority = 1)
    public  void checkToiletSeatsAreDown(){
        Response response = getRequestResponse(toiletSeatURI,getClientAccessToken());

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(200);
        softAssertions.assertThat(response.getBody().jsonPath().getString("data")).as("The data contains a number NULL").isNull();
        softAssertions.assertThat(response.getBody().jsonPath().getString("message")).as("The message is not an empty string").isNotEmpty();
        softAssertions.assertThat(response.getBody().jsonPath().getString("message")).as("The message is not an empty string").contains("You just put the toilet seat down. You're a wonderful roommate!");
        softAssertions.assertThat(response.getBody().jsonPath().getBoolean("success")).as("The success message should be true").isTrue();
        softAssertions.assertAll();
    }

    @Test (priority = 3)
    public void checkToiletSeatsAreDownWhenSendTwice(){
        getRequestResponse(toiletSeatURI, getClientAccessToken());
        Response response = getRequestResponse(toiletSeatURI,getClientAccessToken());

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(200);
        softAssertions.assertThat(response.getBody().jsonPath().getString("data")).as("The data contains a number NULL").isNull();
        softAssertions.assertThat(response.getBody().jsonPath().getString("message")).as("The message is not an empty string").isNotEmpty();
        softAssertions.assertThat(response.getBody().jsonPath().getString("message")).as("The message is not an empty string").contains("Yea, the toilet seat is already down... you slob!");
        softAssertions.assertThat(response.getBody().jsonPath().getBoolean("success")).as("The success message should be true").isTrue();
        softAssertions.assertAll();
    }

    @Test(priority = 2)
    public void checkToiletSeatsAreDownWhenAccessTokenIsNull(){
        Response response = getRequestResponse(toiletSeatURI, null);

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(401);
        softAssertions.assertThat(response.getBody().jsonPath().getString("error")).as("The error message should be: ").isEqualTo("invalid_token");
        softAssertions.assertThat(response.getBody().jsonPath().getString("error_description")).as("The error message description should be: ").isEqualTo("The access token provided is invalid");
        softAssertions.assertAll();
    }

    @Test(priority = 4)
    public void checkToiletSeatsAreDownWhenScopeIsInCorrect(){
        Response response = getRequestResponse(configReader.getProperty("base_api_url")+"toiletseat-own", getClientAccessToken());

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(401);
        softAssertions.assertThat(response.getBody().jsonPath().getString("error")).as("The error message should be: ").isEqualTo("insufficient_scope");
        softAssertions.assertThat(response.getBody().jsonPath().getString("error_description")).as("The error message description should be: ").isEqualTo("The request requires higher privileges than provided by the access token");
        softAssertions.assertAll();
    }

    @Test(priority = 5)
    public void checkToiletSeatsAreDownWhenNoScope(){
        Response response = getRequestResponse(configReader.getProperty("base_api_url"), getClientAccessToken());
        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(404);
    }
}
