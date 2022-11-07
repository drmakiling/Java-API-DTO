import org.junit.jupiter.api.Test;

import org.apache.commons.configuration.ConfigurationException;
import java.io.IOException;

public class RunTest {
    @Test()
    public void login() throws IOException, ConfigurationException, javax.naming.ConfigurationException {
        Customer customer=new Customer();
        customer.callingLoginAPI();
    }
}


