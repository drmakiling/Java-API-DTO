import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.given;

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
    public void callingLoginAPI() throws IOException,
            ConfigurationException, javax.naming.ConfigurationException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .contentType("application/json")
                        .body(
                                "{\"name\":\"string\",\n" +
                                        " \"status\":\"Test\"}"
                        ).
                        when()
                        .post("/customer/api/v1/login").
                        then()
                        .assertThat().statusCode( 200 ).extract
                                ().response();

        JsonPath jsonpath = res.jsonPath();
        token = jsonpath.get("token");
        Utils.setEnvVariable(token);
    }
}
