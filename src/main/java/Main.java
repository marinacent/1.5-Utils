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
                    String dirPath =
                            config.getProperty("dirPath").replace("/", File.separator);
                    String outPath =
                            config.getProperty("outPath").replace("/", File.separator);
                    String txtFilePath =
                            config.getProperty("txtFilePath").replace("/", File.separator);
                    String personPath =
                            config.getProperty("personPath").replace("/", File.separator);
                    String encryptedDataPath =
                            config.getProperty("encryptedDataPath").replace("/", File.separator);
                    String secretKeyPath =
                            config.getProperty("secretKeyPath").replace("/", File.separator);
                    String ivBytesPath =
                            config.getProperty("ivBytesPath").replace("/", File.separator);
                    String decryptedDataPath =
                            config.getProperty("decryptedDataPath").replace("/", File.separator);
                    String personEncryptedDataPath =
                            config.getProperty("personEncryptedDataPath").replace("/", File.separator);
                    String personSecretKeyPath =
                            config.getProperty("personSecretKeyPath").replace("/", File.separator);
                    String personIvBytesPath =
                            config.getProperty("personIvBytesPath").replace("/", File.separator);
                    String personDecryptedDataPath =
                            config.getProperty("personDecryptedDataPath").replace("/", File.separator);

                    try {
                        for (File file : ContentLister.listDirContent(dirPath)) {
                            System.out.println(file.getName());
                        }

                        ContentLister.listDirTree(dirPath);
                        ContentLister.listDirTree(dirPath, outPath);

                        TxtProcessor.printFromTxt(txtFilePath);

                        Serializer.serialize(sophia, personPath);
                        garcia.set((Person) Serializer.deserialize(personPath));
                        if (garcia.get() != null) {
                            System.out.println(garcia.toString());
                        }

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    EncryptedFileSaver.saveEncryptedFile(outPath, encryptedDataPath,
                            secretKeyPath, ivBytesPath);
                    DecriptedFileSaver.saveDecriptedFile(encryptedDataPath, decryptedDataPath,
                            secretKeyPath,ivBytesPath);

                    EncryptedFileSaver.saveEncryptedFile(personPath, personEncryptedDataPath,
                            personSecretKeyPath, personIvBytesPath);
                    DecriptedFileSaver.saveDecriptedFile(personEncryptedDataPath, personDecryptedDataPath,
                            personSecretKeyPath,personIvBytesPath);

                },
                () -> {
                    System.out.println("Couldn't load config.properties. Exiting.");
                    System.exit(1);
                }
        );

    }
}
