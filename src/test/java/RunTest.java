import org.json.JSONException;
import org.junit.jupiter.api.Test;

import org.apache.commons.configuration.ConfigurationException;
import java.io.IOException;

public class RunTest {
    @Test()
    public void saveProfileAddressAPI() throws IOException, ConfigurationException, javax.naming.ConfigurationException, JSONException {
        Customer customer=new Customer();
        customer.callingsaveProfileAddressAPI();
    }
}


