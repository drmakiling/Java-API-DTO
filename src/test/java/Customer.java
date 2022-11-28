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
    public String authorization = "Basic YWx0cnVpc3QtYXBwOmFsdHJ1aXN0LXNlY3JldA==";
    public String contentType = "application/x-www-form-urlencoded";
    public String cookie = "JSESSIONID=71F0FC27EEDF66D625C0EB96AF6F525D; JSESSIONID=F918233017B2B8DE349C49EB0DA7C0A5";
    Object authCookie = cookie;


    public static Cookie JSESSIONCOOKIE() {
        Cookie jsessionIDCookie = new Cookie.Builder("JSESSIONID", "BEC7F52B015BD05EE6CCACDEC30EDC80")
                .setSecured(true)
                .build();

        return jsessionIDCookie;
    }

    public void callAuthCall() {
        Response response =
                given()
                        .log()
                        .all()
                        .relaxedHTTPSValidation()
                        .header("Authorization", authorization)
                        .header("Bearer", token)
                        .header("Content-Type", contentType)
                        .cookie((String) authCookie)
                        .body(
                                "{\n" +
                                        "  \"grant_type\": \"password\",\n" +
                                        "  \"username\": \"qateam+admin+oct12@altruist.com\",\n" +
                                        "  \"password\": \"A!tru1st\",\n" +
                                        "}"
                        )
                        .when()
                        .post("https://platform.test1.altruistnet.tech/idp/oauth/token")
                        .then() .assertThat().statusCode( 200 ).extract
                                ().response();
    }



    public void callingsaveProfileAddressAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException {
        prop.load(file);
        Cookie cookie = JSESSIONCOOKIE();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .log()
                        .all()
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
        token = jsonpath.get("token");
        Utils.setEnvVariable(token);
    }
}
