import org.junit.jupiter.api.Test;

import org.apache.commons.configuration.ConfigurationException;
import java.io.IOException;

public class RunTest {
    @Test()
    public void saveProfileAddress() throws IOException, ConfigurationException, javax.naming.ConfigurationException {
        Customer customer=new Customer();
        customer.callingsaveProfileAddressAPI();
    }
}


