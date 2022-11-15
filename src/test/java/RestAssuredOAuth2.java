import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class RestAssuredOAuth2 {

    public static Response response;
    private String userAdvisorId = System.getenv("756e1d44-89fb-4f25-88e8-9d9ab07d0b00");

    private String oauth2Payload = "{\n" +
            "  \"user_id\": \"" + userAdvisorId + "\",\n" +
            "  \"audience\": \"https://platform.test1.altruistnet.tech\",\n" +
            "  \"grant_type\": \"user_credentials\",\n" +
            "  \"scope\": \"user:advisor\" \n}";

    private static String inputUserPayload = "{\n" +
            "  \"email\": \"qateam+admin+oct12@altruist.com\",\n" +
            "  \"password\": \"A!tru1st\",\n";

    public void userAdminConfigSetup() {
        requestSpecification = given().auth().oauth2(getAccessToken(oauth2Payload))
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON);
    }

    public String getAccessToken(String payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/token")
                .then().extract().response()
                .jsonPath().getString("access_token");
    }

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://platform.test1.altruistnet.tech";
    }

    @Test
    public void inputUser() {
        userAdminConfigSetup();
        response = given(requestSpecification)
                .body(inputUserPayload)
                .post("/user")
                .then().extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

}
















































