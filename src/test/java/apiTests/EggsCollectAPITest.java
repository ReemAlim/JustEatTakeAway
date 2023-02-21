package apiTests;

import commonutils.apiUtils.UtilsApiClass;
import config.ConfigReader;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class EggsCollectAPITest extends UtilsApiClass {
    ConfigReader configReader = new ConfigReader();
    String eggCollectURI = configReader.getProperty("base_api_url") + configReader.getProperty("eggcollect_path");
    SoftAssertions softAssertions = new SoftAssertions();

    @Test(priority = 1)
    public void CheckTheResponseForEggsCollectAPITest(){
        Response response = getRequestResponse(eggCollectURI,getClientAccessToken());

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(200);
        softAssertions.assertThat(response.getBody().jsonPath().getString("data")).as("The data contains a number not NULL").isNotNull();
        softAssertions.assertThat(response.getBody().jsonPath().getString("message"))
                .as("The message contains the same number for the egg counts: ").contains(response.getBody().jsonPath().getString("data"));
        softAssertions.assertThat(response.getBody().jsonPath().getString("message")).as("The message is not an empty string").isNotEmpty();
        softAssertions.assertThat(response.getBody().jsonPath().getBoolean("success")).as("The success message should be true").isTrue();
        softAssertions.assertAll();
    }

    @Test(priority = 3)
    public void CheckWhenScopeIsNotProvided(){
        Response response = getRequestResponse(configReader.getProperty("base_api_url"), getClientAccessToken());

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(404);
    }

    @Test(priority = 4)
    public void CheckTheResponseWhenAccessTokenIsNull() {
        Response response = getRequestResponse(eggCollectURI, null);

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(401);
        softAssertions.assertThat(response.getBody().jsonPath().getString("error")).as("The access token is invalid: ").contains("invalid_token");
        softAssertions.assertThat(response.getBody().jsonPath().getString("error_description")).as("The error description is: ").contains("The access token provided is invalid");
        softAssertions.assertAll();
    }

    @Test(priority = 5)
    public void CheckTheResponseWhenAccessTokenContainsSpace() {
        Response response = getRequestResponse(eggCollectURI, " ");

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(401);
        softAssertions.assertThat(response.getBody().jsonPath().getString("error")).as("The access token is invalid: ").contains("invalid_request");
        softAssertions.assertThat(response.getBody().jsonPath().getString("error_description")).as("The error description is: ").contains("Malformed auth header");
        softAssertions.assertAll();
    }

    @Test(priority = 2)
    public void CheckTheMessageAfterCollectingTwice(){
        getRequestResponse(eggCollectURI, getClientAccessToken());
        Response response = getRequestResponse(eggCollectURI, getClientAccessToken());

        softAssertions.assertThat(response.getStatusCode()).as("The status code is equal to: ").isEqualTo(200);
        softAssertions.assertThat(response.getBody().jsonPath().getString("message")).as("The message should be: ").contains("Hey, give the ladies a break. Makin' eggs ain't easy!");
        softAssertions.assertAll();
    }
}
