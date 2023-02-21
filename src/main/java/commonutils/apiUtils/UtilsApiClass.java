package commonutils.apiUtils;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UtilsApiClass {
    private static final ConfigReader configReader = new ConfigReader();

    public UtilsApiClass() {
    }

    public static String getClientAccessToken() {
        RestAssured.baseURI = configReader.getProperty("base_uri") + "/token";

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic("JustEatTakeAway", "f4f87c22f674e65be5d8f82783caa635")
                .formParam("grant_type", "client_credentials")
                .formParam("eggs-count", "read")
                .when()
                .post().thenReturn();

        return response.jsonPath().getString("access_token");
    }

    public static Response getRequestResponse(String uri, String accessToken) {
        RestAssured.baseURI = uri;
        return RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .post().thenReturn();

    }

    public static ResponseBody getResponseBody(String uri, String accessToken) {
        return getRequestResponse(uri, accessToken).getBody();
    }
}
