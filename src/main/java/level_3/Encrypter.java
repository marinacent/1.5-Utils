package level_3;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encrypter {
    private final SecretKey secretKey;
    private final byte[] ivBytes;
    private static final SecureRandom random = new SecureRandom();
    private final Cipher cipher;

    public Encrypter() throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeyException {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        this.secretKey = keyGen.generateKey();

        this.ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
    }

    public byte[] encrypt(String inPath) throws IOException, IllegalBlockSizeException, BadPaddingException {
        byte[] fileAsBytes = Files.readAllBytes((Paths.get(inPath)));
        return cipher.doFinal(fileAsBytes);
    }

    public void writeEncryptedFile(byte[] encryptedData, String outPath, String keyPath, String ivPath)
            throws IOException {
        Files.write(Paths.get(outPath), encryptedData);
        Files.write(Paths.get(keyPath), secretKey.getEncoded());
        Files.write(Paths.get(ivPath), ivBytes);
    }





}
