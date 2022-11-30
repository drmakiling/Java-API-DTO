import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class RestAssuredOAuth2 {
    String oauth_token ;
    @BeforeTest
    public void RestAssuredOauth2Test() {
        oauth_token = RestAssured.given().auth().basic("username", "password")

                .queryParams("client_id", "client_id_value")
                .queryParams("client_secret", "client_secret_value")
                .queryParams("grant_type", "authorization_code")
                .queryParams("redirect_uri", "https://platform.test1.altruistnet.tech")
                .queryParams("other_params", "sample_param")
                .when().get("https://platform.test1.altruistnet.tech/oauth2/access_token")
                .then()
                .statusCode(200)
                .extract().header("access_token");
    }
}












































