package level_3;

import level_2.ConfigLoader;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        String configPath = "config.properties";
        Optional<ConfigLoader> configOptional = ConfigLoader.load(configPath);

        configOptional.ifPresentOrElse(
                config -> {
                    String outPath = config.getPath("outPath");
                    String personPath = config.getPath("personPath");
                    String encryptedDataPath = config.getPath("encryptedDataPath");
                    String secretKeyPath = config.getPath("secretKeyPath");
                    String ivBytesPath = config.getPath("ivBytesPath");
                    String decryptedDataPath = config.getPath("decryptedDataPath");
                    String personEncryptedDataPath = config.getPath("personEncryptedDataPath");
                    String personSecretKeyPath = config.getPath("personSecretKeyPath");
                    String personIvBytesPath = config.getPath("personIvBytesPath");
                    String personDecryptedDataPath = config.getPath("personDecryptedDataPath");

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
