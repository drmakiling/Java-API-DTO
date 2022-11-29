import org.apache.commons.configuration.ConfigurationException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RunTest {

    // pii (post)
    @Test()
    public void saveProfileAddressAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        String res = customer.callingsaveProfileAddressAPI();
        System.out.println("Response: " + res);
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


