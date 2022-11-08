import io.restassured.RestAssured;
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
    public void callingsaveProfileAddressAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .contentType("application/json")
                        .body(
                                "{\n" +
                                        "  \"addr1\": \"123 Test Street\",\n" +
                                        "  \"addr2\": \"Unit 7\",\n" +
                                        "  \"addr3\": \"Test\",\n" +
                                        "  \"addressType\": \"BUSINESS\",\n" +
                                        "  \"city\": \"Redondo Beach\",\n" +
                                        "  \"country\": \"USA\",\n" +
                                        "  \"createdBy\": \"Test\",\n" +
                                        "  \"id\": \"<<profileId>>\",\n" +
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
