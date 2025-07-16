package level_2;

import java.io.File;
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

    public String getPath(String key) {
        String path = properties.getProperty(key);
        return path.replace("/", File.separator);
    }

}
