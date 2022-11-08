import org.apache.commons.configuration.PropertiesConfiguration;

import javax.naming.ConfigurationException;
import java.io.IOException;

public class Utils {
    public static String setEnvVariable(String variable) throws
            IOException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty("token", variable);
        config.save();
        return variable;
    }
}
