import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
    private String username = "qateam+admin+oct12@altruist.com";
    private String password = "A!tru1st";


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
                        .formParam("username", username)
                        .formParam("password", password)
                        .cookie("JSESSIONID","71F0FC27EEDF66D625C0EB96AF6F525D", "F918233017B2B8DE349C49EB0DA7C0A5")
                        .when()
                        .post("/idp/oauth/token")
                        .then().statusCode(200).extract().response().asString();;

        JsonPath authPayload = new JsonPath(res);
        String accessToken = authPayload.getString("access_token");
        return accessToken;

    }

    public Response callingsaveProfileAddressAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String addr1 = prop.getProperty("addr1");
        String addr2 = prop.getProperty("addr2");
        String addr3 = prop.getProperty("addr3");
        String addressType = prop.getProperty("addressType");
        String city = prop.getProperty("city");
        String country = prop.getProperty("country");
        String createdBy = prop.getProperty("createdBy");
        String id = prop.getProperty("id");
        String state = prop.getProperty("state");
        String status = prop.getProperty("status");
        String updatedBy = prop.getProperty("updatedBy");
        String zipCode = prop.getProperty("zipCode");

        String body = String.format(
                "{\n" +
                        "  \"addr1\": \"%s\",\n" +
                        "  \"addr2\": \"%s\",\n" +
                        "  \"addr3\": \"%s\",\n" +
                        "  \"addressType\": \"%s\",\n" +
                        "  \"city\": \"%s\",\n" +
                        "  \"country\": \"%s\",\n" +
                        "  \"createdBy\": \"%s\",\n" +
                        "  \"id\": \"%s\",\n" +
                        "  \"state\": \"%s\",\n" +
                        "  \"status\": \"%s\",\n" +
                        "  \"updatedBy\": \"%s\",\n" +
                        "  \"zipCode\": \"%s\"\n" +
                        "}", addr1, addr2, addr3, addressType, city, country, createdBy, id, state, status, updatedBy, zipCode);
        Response res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .body(body)
                        .cookie(cookie)
                        .when()
                        .post("/pii/api/profile-address/" + id)
                        .then()
                        .extract().response();

        // .statusCode(200)
        //Assertions.assertEquals(200, res.statusCode());

       // JsonPath authPayload = new JsonPath(res);
        return res;
    }
    public Response callinggetAddressByProfileIdAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String id = prop.getProperty("id");
        Response res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .cookie(cookie)
                        .when()
                        .get("/pii/api/address/" + id).
                        then()
                        .extract().response();

        return res;
    }

    public Response callingupdateUserProfileAddressAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String addr1 = prop.getProperty("addr1");
        String addr2 = prop.getProperty("addr2");
        String addr3 = prop.getProperty("addr3");
        String addressType = prop.getProperty("addressType");
        String city = prop.getProperty("city");
        String country = prop.getProperty("country");
        String createdBy = prop.getProperty("createdBy");
        String id = prop.getProperty("id");
        String state = prop.getProperty("state");
        String status = prop.getProperty("status");
        String updatedBy = prop.getProperty("updatedBy");
        String zipCode = prop.getProperty("zipCode");

        String body = String.format(
                "{\n" +
                        "  \"addr1\": \"%s\",\n" +
                        "  \"addr2\": \"%s\",\n" +
                        "  \"addr3\": \"%s\",\n" +
                        "  \"addressType\": \"%s\",\n" +
                        "  \"city\": \"%s\",\n" +
                        "  \"country\": \"%s\",\n" +
                        "  \"createdBy\": \"%s\",\n" +
                        "  \"id\": \"%s\",\n" +
                        "  \"state\": \"%s\",\n" +
                        "  \"status\": \"%s\",\n" +
                        "  \"updatedBy\": \"%s\",\n" +
                        "  \"zipCode\": \"%s\"\n" +
                        "}", addr1, addr2, addr3, addressType, city, country, createdBy, id, state, status, updatedBy, zipCode);
        Response res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .body(body)
                        .cookie(cookie)
                        .when()
                        .put("/pii/api/user-profiles/" + id + "/address").
                        then()
                        .extract().response();

        return res;
    }

    public Response callinggetsTheUserProfilesByUserIDsAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String orgId = prop.getProperty("orgId");
        String page = prop.getProperty("page");
        String size = prop.getProperty("size");
        String id = prop.getProperty("id");

        String body = String.format(
                "{" +
                        "\"orgId\": \"%s\",\n" +
                        "\"page\": \"%s\",\n" +
                        "\"size\": \"%s\",\n" +
                        "\"userIds\": [\"%s\"]\n" +
                "}", orgId, page, size, id);

        Response res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .body(body)
                        .cookie(cookie)
                        .when()
                        .post("/userAcct/api/v2/users/user-profiles/_search").
                        then()
                        .extract().response();

        return res;
    }

    public Response callinggetUserByUserProfileIdAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException, JSONException {
        String accessToken = callAuthCall();
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String orgId = prop.getProperty("orgId");
        String id = prop.getProperty("id");
        Response res =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .cookie(cookie)
                        .when()
                        .get("/userAcct/api/orgs/" + orgId + "/user/by/profile/" + id).
                        then()
                        .extract().response();

        return res;
    }

}
