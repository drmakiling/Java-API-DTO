import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.IOException;

public class RunTest {

    // Auth Call
    @Test()
    public void callAuthCall() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        String res = customer.callAuthCall();
        System.out.println("Response: " + res);
    }

    // pii (post)
    @Test()
    public void saveProfileAddressAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        Response res = customer.callingsaveProfileAddressAPI();
        int statusCode = res.getStatusCode();

        System.out.println("\nStatus Code: " + statusCode);
        System.out.println("\n");
        Assertions.assertEquals(200, statusCode);
        Assert.assertEquals(statusCode, 200, "Correct status code is not returned");
        Assert.assertTrue(Status.SUCCESS.matches(res.statusCode()));
        Assert.assertFalse(Status.FAILURE.matches(res.statusCode()));
        String addr1 = res.jsonPath().getString("address.addr1");
        Assertions.assertEquals("123 Test Street", addr1);
        String addr2 = res.jsonPath().getString("address.addr2");
        Assertions.assertEquals("Unit 7", addr2);
        String addr3 = res.jsonPath().getString("address.addr3");
        Assertions.assertEquals("Test", addr3);
        String city = res.jsonPath().getString("address.city");
        Assertions.assertEquals("Redondo Beach", city);
        String state = res.jsonPath().getString("address.state");
        Assertions.assertEquals("CA", state);
        String zipCode = res.jsonPath().getString("address.zipCode");
        Assertions.assertEquals("90278", zipCode);
        String country = res.jsonPath().getString("address.country");
        Assertions.assertEquals("USA", country);
        String updatedBy = res.jsonPath().getString("address.updatedBy");
        Assertions.assertEquals("Test", updatedBy);
        String addressType = res.jsonPath().getString("address.addressType");
        Assertions.assertEquals("BUSINESS", addressType);
    }
// pii (get)
    @Test()
    public void getAddressByProfileIdAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        String res = customer.callinggetAddressByProfileIdAPI();
        System.out.println("Response: " + res);
    }

    // pii (put)
    @Test()
    public void updateUserProfileAddressesAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        String res = customer.callingupdateUserProfileAddressesAPI();
        System.out.println("Response: " + res);
    }

    //userAcct (post)
    @Test()
    public void getsTheUserProfilesByUserIDsAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        String res = customer.callinggetsTheUserProfilesByUserIDsAPI();
        System.out.println("Response: " + res);
    }

    //userAcct (get)
    @Test()
    public void getUserByUserProfileIdAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        String res = customer.callinggetUserByUserProfileIdAPI();
        System.out.println("Response: " + res);
    }

}


