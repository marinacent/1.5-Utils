public class DecriptedFileSaver {

    public static void saveDecriptedFile(String inPath, String outPath, String keyPath, String ivPath) {
        try {
            Decrypter decrypter = new Decrypter(keyPath, ivPath);
            byte[] decryptedData = decrypter.decrypt(inPath);
            decrypter.writeDecryptedFile(decryptedData, outPath);

        } catch (Exception e) {
            System.out.println("%nCouldn't save decrypted file%n");
        }

    }
}
