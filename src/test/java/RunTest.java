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

//        Assert.assertEquals(statusCode, 200, "Test has failed. Correct status code is not returned.");

        try {
            Assert.assertEquals(statusCode, 200);
        }
        catch (AssertionError e) {
            System.out.print("Failure! Status Code: " + statusCode);
            if (statusCode==400)
                System.out.println(" Bad request!");
            else if (statusCode==401)
                System.out.println(" Unauthorized!");
            else if (statusCode==403)
                System.out.println(" Forbidden");
            else if (statusCode==404)
                System.out.println(" Not Found!");
            else if (statusCode==500)
                System.out.println(" Server Error!");
            else
                System.out.println(" Unknown error!");
            throw e;
        }
        System.out.println("Successful run!");

        String addr1 = res.jsonPath().getString("address.addr1");
        Assert.assertEquals("123 Test Street", addr1);
        String addr2 = res.jsonPath().getString("address.addr2");
        Assert.assertEquals("Unit 7", addr2);
        String addr3 = res.jsonPath().getString("address.addr3");
        Assert.assertEquals("Test", addr3);
        String city = res.jsonPath().getString("address.city");
        Assert.assertEquals("Redondo Beach", city);
        String state = res.jsonPath().getString("address.state");
        Assert.assertEquals("CA", state);
        String zipCode = res.jsonPath().getString("address.zipCode");
        Assert.assertEquals("90278", zipCode);
        String country = res.jsonPath().getString("address.country");
        Assert.assertEquals("USA", country);
        String updatedBy = res.jsonPath().getString("address.updatedBy");
        Assert.assertEquals("Test", updatedBy);
        String addressType = res.jsonPath().getString("address.addressType");
        Assert.assertEquals("BUSINESS", addressType);

    }
// pii (get)
    @Test()
    public void getAddressByProfileIdAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        Response res = customer.callinggetAddressByProfileIdAPI();
        int statusCode = res.getStatusCode();

//        Assert.assertEquals(statusCode, 200, "Test has failed. Correct status code is not returned.");

        try {
            Assert.assertEquals(statusCode, 200);
        }
        catch (AssertionError e) {
            System.out.print("Failure! Status Code: " + statusCode);
            if (statusCode==400)
                System.out.println(" Bad request!");
            else if (statusCode==401)
                System.out.println(" Unauthorized!");
            else if (statusCode==403)
                System.out.println(" Forbidden");
            else if (statusCode==404)
                System.out.println(" Not Found!");
            else if (statusCode==500)
                System.out.println(" Server Error!");
            else
                System.out.println(" Unknown error!");
            throw e;
        }
        System.out.println("Successful run!");

        String id = res.jsonPath().getString("id");
        Assert.assertNotNull(id);
        String addr1 = res.jsonPath().getString("addr1");
        Assert.assertNotNull(addr1);
        String addr2 = res.jsonPath().getString("addr2");
        Assert.assertNotNull(addr2);
        String addr3 = res.jsonPath().getString("addr3");
        Assert.assertNotNull(addr3);
        String city = res.jsonPath().getString("city");
        Assert.assertNotNull(city);
        String state = res.jsonPath().getString("state");
        Assert.assertNotNull(state);
        String zipCode = res.jsonPath().getString("zipCode");
        Assert.assertNotNull(zipCode);
        String country = res.jsonPath().getString("country");
        Assert.assertNotNull(country);
        String createdBy = res.jsonPath().getString("createdBy");
        Assert.assertNotNull(createdBy);
        String status = res.jsonPath().getString("status");
        Assert.assertNotNull(status);
        String addressType = res.jsonPath().getString("addressType");
        Assert.assertNotNull(addressType);


    }

    // pii (put)
    @Test()
    public void updateUserProfileAddressAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        Response res = customer.callingupdateUserProfileAddressAPI();

        int statusCode = res.getStatusCode();

//        Assert.assertEquals(statusCode, 200, "Test has failed. Correct status code is not returned.");

        try {
            Assert.assertEquals(statusCode, 200);
        }
        catch (AssertionError e) {
            System.out.print("Failure! Status Code: " + statusCode);
            if (statusCode==400)
                System.out.println(" Bad request!");
            else if (statusCode==401)
                System.out.println(" Unauthorized!");
            else if (statusCode==403)
                System.out.println(" Forbidden");
            else if (statusCode==404)
                System.out.println(" Not Found!");
            else if (statusCode==500)
                System.out.println(" Server Error!");
            else
                System.out.println(" Unknown error!");
            throw e;
        }
        System.out.println("Successful run!");

        String addr1 = res.jsonPath().getString("addr1");
        Assertions.assertEquals("123 Test Street", addr1);
        String addr2 = res.jsonPath().getString("addr2");
        Assert.assertEquals("Unit 7", addr2);
        String addr3 = res.jsonPath().getString("addr3");
        Assert.assertEquals("Test", addr3);
        String city = res.jsonPath().getString("city");
        Assert.assertEquals("Redondo Beach", city);
        String state = res.jsonPath().getString("state");
        Assert.assertEquals("CA", state);
        String zipCode = res.jsonPath().getString("zipCode");
        Assert.assertEquals("90278", zipCode);
        String country = res.jsonPath().getString("country");
        Assert.assertEquals("USA", country);
        String updatedBy = res.jsonPath().getString("updatedBy");
        Assert.assertEquals("Test", updatedBy);
        String addressType = res.jsonPath().getString("addressType");
        Assert.assertEquals("BUSINESS", addressType);

        String id = res.jsonPath().getString("id");
        Assert.assertNotNull(id);
        Assert.assertNotNull(addr1);
        Assert.assertNotNull(addr2);
        Assert.assertNotNull(addr3);
        Assert.assertNotNull(city);
        Assert.assertNotNull(state);
        Assert.assertNotNull(zipCode);
        Assert.assertNotNull(country);
        String createdBy = res.jsonPath().getString("createdBy");
        Assert.assertNotNull(createdBy);
        Assert.assertNotNull(updatedBy);
        String status = res.jsonPath().getString("status");
        Assert.assertNotNull(status);
        Assert.assertNotNull(addressType);


    }

    //userAcct (post)
    @Test()
    public void getsTheUserProfilesByUserIDsAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        Response res = customer.callinggetsTheUserProfilesByUserIDsAPI();

        int statusCode = res.getStatusCode();

//        Assert.assertEquals(statusCode, 200, "Test has failed, Correct status code is not returned.");

        try {
            Assert.assertEquals(statusCode, 200);
        }
        catch (AssertionError e) {
            System.out.print("Failure! Status Code: " + statusCode);
            if (statusCode==400)
                System.out.println(" Bad request!");
            else if (statusCode==401)
                System.out.println(" Unauthorized!");
            else if (statusCode==403)
                System.out.println(" Forbidden");
            else if (statusCode==404)
                System.out.println(" Not Found!");
            else if (statusCode==500)
                System.out.println(" Server Error!");
            else
                System.out.println(" Unknown error!");
            throw e;
        }
        System.out.println("Successful run!");

        String page = res.jsonPath().getString("page");
        Assert.assertNotNull(page);
        String size = res.jsonPath().getString("size");
        Assert.assertNotNull(size);
        String total = res.jsonPath().getString("total");
        Assert.assertNotNull(total);
        String items = res.jsonPath().getString("items");
        Assert.assertNotNull(items);

    }

    //userAcct (get)
    @Test()
    public void getUserByUserProfileIdAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer = new Customer();
        Response res = customer.callinggetUserByUserProfileIdAPI();

        int statusCode = res.getStatusCode();

//        Assert.assertEquals(statusCode, 200, "Test has failed. Correct status code is not returned.");

        try {
            Assert.assertEquals(statusCode, 200);
        } catch (AssertionError e) {
            System.out.print("Failure! Status Code: " + statusCode);
            if (statusCode == 400)
                System.out.println(" Bad request!");
            else if (statusCode == 401)
                System.out.println(" Unauthorized!");
            else if (statusCode == 403)
                System.out.println(" Forbidden");
            else if (statusCode == 404)
                System.out.println(" Not Found!");
            else if (statusCode == 500)
                System.out.println(" Server Error!");
            else
                System.out.println(" Unknown error!");
            throw e;
        }
        System.out.println("Successful run!");

        String id = res.jsonPath().getString("id");
        Assert.assertNotNull(id);
        String profileId = res.jsonPath().getString("profileId");
        Assert.assertNotNull(profileId);
        String created = res.jsonPath().getString("created");
        Assert.assertNotNull(created);
        String updated = res.jsonPath().getString("updated");
        Assert.assertNotNull(updated);
        String role = res.jsonPath().getString("role");
        Assert.assertNotNull(role);
        String createdBy = res.jsonPath().getString("createdBy");
        Assert.assertNotNull(createdBy);
        String updatedBy = res.jsonPath().getString("updatedBy");
        Assert.assertNotNull(updatedBy);
        String status = res.jsonPath().getString("status");
        Assert.assertNotNull(status);
        String apexEnabled = res.jsonPath().getString("apexEnabled");
        Assert.assertNotNull(apexEnabled);

    }
}


