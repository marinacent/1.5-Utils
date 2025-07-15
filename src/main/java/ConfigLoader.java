import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader(String fileName) {
        properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Couldn't access " + fileName);
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


}
