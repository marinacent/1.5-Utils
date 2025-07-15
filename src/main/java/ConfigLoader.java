import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader(String fileName) throws IOException {
        properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new FileNotFoundException("Couldn't access " + fileName);
            }

            properties.load(input);
        }

    }

    public String getProperty(String key) {
        return properties.getProperty(key);
        }
        
    }
