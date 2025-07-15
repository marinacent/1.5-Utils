import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryptor {
    private KeyGenerator keyGen;
    private SecretKey secretKey;
    private byte[] ivBytes;
    private static final SecureRandom random = new SecureRandom();
    private IvParameterSpec ivParameterSpec;
    private Cipher cipher;

    public Encryptor() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        this.keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        this.secretKey = keyGen.generateKey();

        this.ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        this.ivParameterSpec = new IvParameterSpec(ivBytes);

        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
    }

    public byte[] encrypt(String inPath) throws IOException, IllegalBlockSizeException, BadPaddingException {
        byte[] fileAsBytes = Files.readAllBytes((Paths.get(inPath)));
        return cipher.doFinal(fileAsBytes);
    }

    public void writeEncryptedFile(byte[] encriptedData, String outPath, String keyPath, String ivPath)
            throws IOException {
        Files.write(Paths.get(outPath), encriptedData);
        Files.write(Paths.get(keyPath), secretKey.getEncoded());
        Files.write(Paths.get(ivPath), ivBytes);
    }



}
