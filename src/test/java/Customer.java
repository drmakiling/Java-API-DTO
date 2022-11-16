import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;

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

    public static Cookie JSESSIONCOOKIE() {
        Cookie jsessionIDCookie = new Cookie.Builder("JSESSIONID", "BEC7F52B015BD05EE6CCACDEC30EDC80")
                .setSecured(true)
                .build();

        return jsessionIDCookie;
    }
    public void callingsaveProfileAddressAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException {
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .relaxedHTTPSValidation()
                        .headers("Content-Type", "application/json")
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
                        .post("/pii/api/address/ddad7ffe-dc65-4b04-bd3a-80086a701110").
                        then()
                        .assertThat().statusCode( 200 ).extract
                                ().response();

        JsonPath jsonpath = res.jsonPath();
        token = jsonpath.get("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImRldi1qd3QtMSJ9.eyJhcmNoZXR5cGUiOiJBRFZJU09SIiwiY2xlYXJpbmdfcGxhdGZvcm0iOiJBTFRSVUlTVF9DTEVBUklORyIsInVzZXJfbmFtZSI6Ijc1NmUxZDQ0LTg5ZmItNGYyNS04OGU4LTlkOWFiMDdkMGIwMCIsIm9yZ19pZCI6IjBiMmNiM2Y2LWIzNTctNGFmZi1hNGU3LTMyZDM3NzFhM2I4YSIsInNjb3BlIjpbInVzZXJzLmFkbWluIl0sImFjdHVhbF91c2VyX25hbWUiOiI3NTZlMWQ0NC04OWZiLTRmMjUtODhlOC05ZDlhYjA3ZDBiMDAiLCJleHAiOjE2Njg2Mzg1MTksIm5vbmNlIjoiNGU1MWZjYzIiLCJhdXRob3JpdGllcyI6WyJhbHRydWlzdF9jbGVhcmluZy51c2VyIiwidXNlcnMuYWRtaW4iLCJvYXV0aC51c2VyIl0sImp0aSI6IjZmNjI5MTE0LTEzNjQtNGQwYS1iZDQwLTg0OTY0MTFmNjEwMSIsImNsaWVudF9pZCI6ImFsdHJ1aXN0LWFwcCJ9.pwMA0uAwm_AQ_cE6EhlCFMIs8aQSGxzZ_F-hU2v1Te9EnJKY_KYTEoc582x7BJAqg6Ba4E3Q1VzpQoBrTeXHZWs-4TgywR0VyD4aI2XhhKgHYkvbN9XLDAYK82SXiVVq0g4jV3JFR0b-X8SCSRQeZMAMUjVYipFC1MM2P04EpZsJhHpNL2PeFuoHuwxdpgQZR0kPINKNfbiIRik_HCzqCglPzacz6Qebmv_90t-ga9-I8ehSydoHnM9aaWz0B9YHShmQmmBu08H6nHVYCHR6QT6OKjAt2ntZrcIzT2LyqN5bWIyNOqwSjVA1sKbxdZxYcVB9rQQTettPuPUS6-hLHQ");
        Utils.setEnvVariable(token);
    }
}
