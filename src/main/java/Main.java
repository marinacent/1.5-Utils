import level_1.ContentLister;
import level_1.Person;
import level_1.Serializer;
import level_1.TxtProcessor;
import level_2.ConfigLoader;
import level_3.DecryptedFileSaver;
import level_3.EncryptedFileSaver;

import java.io.File;
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
                    String dirPath = config.getPath("dirPath");
                    String outPath = config.getPath("outPath");
                    String txtFilePath = config.getPath("txtFilePath");
                    String personPath = config.getPath("personPath");
                    String encryptedDataPath = config.getPath("encryptedDataPath");
                    String secretKeyPath = config.getPath("secretKeyPath");
                    String ivBytesPath = config.getPath("ivBytesPath");
                    String decryptedDataPath = config.getPath("decryptedDataPath");
                    String personEncryptedDataPath = config.getPath("personEncryptedDataPath");
                    String personSecretKeyPath = config.getPath("personSecretKeyPath");
                    String personIvBytesPath = config.getPath("personIvBytesPath");
                    String personDecryptedDataPath = config.getPath("personDecryptedDataPath");

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
                    DecryptedFileSaver.saveDecryptedFile(encryptedDataPath, decryptedDataPath,
                            secretKeyPath, ivBytesPath);

                    EncryptedFileSaver.saveEncryptedFile(personPath, personEncryptedDataPath,
                            personSecretKeyPath, personIvBytesPath);
                    DecryptedFileSaver.saveDecryptedFile(personEncryptedDataPath, personDecryptedDataPath,
                            personSecretKeyPath, personIvBytesPath);

                },
                () -> {
                    System.out.println("Couldn't load config.properties. Exiting.");
                    System.exit(1);
                }
        );

    }
}
