import javax.naming.ConfigurationException;
import java.io.IOException;

public class Utils {
    public static String setEnvVariable(String variable) throws
            IOException, ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty("token", variable);
        config.save();
        return variable;
    }
}
