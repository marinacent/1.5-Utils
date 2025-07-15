import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decrypter {
    private final Cipher cipher;

    public Decrypter(String keyPath, String ivPath) throws IOException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {

        byte[] keyBytes = Files.readAllBytes(Paths.get(keyPath));
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        byte[] ivBytes = Files.readAllBytes(Paths.get(ivPath));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

    }

    public byte[] decrypt(String inPath) throws IllegalBlockSizeException, BadPaddingException,
            IOException {

        byte[] encryptedData = Files.readAllBytes(Paths.get(inPath));
        return cipher.doFinal(encryptedData);
    }

    public void writeDecryptedFile(byte[] decriptedData, String outPath) throws IOException {
        Files.write(Paths.get(outPath), decriptedData);
    }
}
