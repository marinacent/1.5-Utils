import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    private ConfigLoader(Properties properties) {
        this.properties = properties;
    }

    public static Optional<ConfigLoader> load(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Couldn't access " + fileName);
                return Optional.empty();
            }
            properties.load(input);
            return Optional.of(new ConfigLoader(properties));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public ConfigLoader(String fileName) throws IOException {
        this.properties = new Properties();

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
