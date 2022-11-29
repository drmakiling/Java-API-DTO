import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Customer {
    Properties prop=new Properties();
    FileInputStream file;
    {
        try
        {
            file = new FileInputStream("./src/test/resources/config.properties");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String token;
    public String authorization = "Basic YWx0cnVpc3QtYXBwOmFsdHJ1aXN0LXNlY3JldA==";
    public String contentType = "application/x-www-form-urlencoded";


    public static Cookie JSESSIONCOOKIE() {
        Cookie jsessionIDCookie = new Cookie.Builder("JSESSIONID", "BEC7F52B015BD05EE6CCACDEC30EDC80")
                .setSecured(true)
                .build();

        return jsessionIDCookie;
    }

    public String callAuthCall() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", authorization)
                        .header("Content-Type", contentType)
                        .formParam("grant_type", "password")
                        .formParam("username", "qateam+admin+oct12@altruist.com")
                        .formParam("password", "A!tru1st")
                        .cookie("JSESSIONID","71F0FC27EEDF66D625C0EB96AF6F525D", "F918233017B2B8DE349C49EB0DA7C0A5")
                        .when()
                        .post("/idp/oauth/token")
                        .then().statusCode(200).extract().response().asString();;

        JsonPath authPayload = new JsonPath(res);
        String accessToken = authPayload.getString("access_token");
        return accessToken;

    }

    public String callingsaveProfileAddressAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .cookie(cookie)
                        .body(
                                "{\n" +
                                        "  \"addr1\": \"123 Test Street\",\n" +
                                        "  \"addr2\": \"Unit 7\",\n" +
                                        "  \"addr3\": \"Test\",\n" +
                                        "  \"addressType\": \"BUSINESS\",\n" +
                                        "  \"city\": \"Redondo Beach\",\n" +
                                        "  \"country\": \"USA\",\n" +
                                        "  \"createdBy\": \"Test\",\n" +
                                        "  \"id\": \"ddad7ffe-dc65-4b04-bd3a-80086a701110\",\n" +
                                        "  \"state\": \"CA\",\n" +
                                        "  \"status\": \"Test\",\n" +
                                        "  \"updatedBy\": \"Test\",\n" +
                                        "  \"zipCode\": \"90278\"\n" +
                                        "}"
                        ).
                        when()
                        .post("/pii/api/profile-address/ddad7ffe-dc65-4b04-bd3a-80086a701110").
                        then()
                        .statusCode(200).extract().response().asString();

       // JsonPath authPayload = new JsonPath(res);
        return res;
    }
    public String callinggetAddressByProfileIdAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .cookie(cookie)
                        .when()
                        .get("/pii/api/address/ddad7ffe-dc65-4b04-bd3a-80086a701110").
                        then()
                        .statusCode(200).extract().response().asString();

        return res;
    }

    public String callingupdateUserProfileAddressesAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .cookie(cookie)
                        .body(
                                "{\n" +
                                "  \"addr1\": \"123 Test Street\",\n" +
                                "  \"addr2\": \"Unit 7\",\n" +
                                "  \"addr3\": \"Test\",\n" +
                                "  \"addressType\": \"BUSINESS\",\n" +
                                "  \"city\": \"Redondo Beach\",\n" +
                                "  \"country\": \"USA\",\n" +
                                "  \"createdBy\": \"Test\",\n" +
                                "  \"id\": \"ddad7ffe-dc65-4b04-bd3a-80086a701110\",\n" +
                                "  \"state\": \"CA\",\n" +
                                "  \"status\": \"Test\",\n" +
                                "  \"updatedBy\": \"Test\",\n" +
                                "  \"zipCode\": \"90278\"\n" +
                                "}")
                        .when()
                        .put("/pii/api/user-profiles/ddad7ffe-dc65-4b04-bd3a-80086a701110/address").
                        then()
                        .statusCode(200).extract().response().asString();

        return res;
    }

    public String callinggetsTheUserProfilesByUserIDsAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .cookie(cookie)
                        .body(
                                "{\n" +
                                        "  \"orgId\": \"0b2cb3f6-b357-4aff-a4e7-32d3771a3b8a\",\n" +
                                        "  \"page\": 0,\n" +
                                        "  \"size\": 0,\n" +
                                        "  \"userIds\": [\n" +
                                        "    \"ddad7ffe-dc65-4b04-bd3a-80086a701110\"\n" +
                                        "  ]\n" +
                                        "}"
                        ).
                        when()
                        .post("/userAcct/api/v2/users/user-profiles/_search").
                        then()
                        .statusCode(200).extract().response().asString();

        return res;
    }

    public String callinggetUserByUserProfileIdAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .cookie(cookie)
                        .when()
                        .get("/userAcct/api/orgs/0b2cb3f6-b357-4aff-a4e7-32d3771a3b8a/user/by/profile/ddad7ffe-dc65-4b04-bd3a-80086a701110").
                        then()
                        .statusCode(200).extract().response().asString();

        return res;
    }

}
