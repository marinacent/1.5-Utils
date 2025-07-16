package level_3;

public class EncryptedFileSaver {

    public static void saveEncryptedFile(String inPath, String outPath, String keyPath, String ivPath) {
        try {
            Encrypter encrypter = new Encrypter();
            byte[] encryptedData = encrypter.encrypt(inPath);
            encrypter.writeEncryptedFile(encryptedData, outPath, keyPath, ivPath);
        } catch (Exception e) {
            System.out.printf("%nCouldn't save encrypted file%n");
        }
    }
}
