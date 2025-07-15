import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        String configPath = "config.properties";
        Optional<ConfigLoader> configOptional = ConfigLoader.load(configPath);

        Person sophia = new Person("Sophia Garcia", 45);
        AtomicReference<Object> garcia = new AtomicReference<>();

        configOptional.ifPresentOrElse(
                config -> {
                    String dirPath = config.getProperty("dirPath");
                    String outPath = config.getProperty("outPath");
                    String txtFilePath = config.getProperty("txtFilePath");
                    String personPath = config.getProperty("personPath");

                    try {
                        for (File file : ContentLister.listDirContent(dirPath)) {
                            System.out.println(file.getName());
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        ContentLister.listDirTree(dirPath);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        ContentLister.listDirTree(dirPath, outPath);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        TxtProcessor.printFromTxt(txtFilePath);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        Serializer.serialize(sophia, personPath);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        garcia.set((Person) Serializer.deserialize(personPath));
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    if (garcia.get() != null) {
                        System.out.println(garcia.toString());
                    }


                },
                () -> {
                    System.out.println("Couldn't load config.properties. Exiting.");
                    System.exit(1);
                }
        );

    }
}
