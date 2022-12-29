package Cryptography;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Cryptography {

    static final String utf8 = "utf-8";

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static String getHash(String input) throws NoSuchAlgorithmException {
        return toHexString(getSHA(input));
    }

    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedMesssageBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedMesssageBase64;
    }

    public static String decrypt(String encryptedBase64, SecretKey secretKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, "UTF-8");
        return decryptedText;
    }

    public static SecretKey generateSecretKey(String password) throws Exception {

        byte[] keyBytes = Arrays.copyOf(password.getBytes(utf8), 24);
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }
    
     public static String getHashShar_1(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < byteData.length; ++i) {

                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            }

            return sb.toString();

        } catch (NoSuchAlgorithmException exc) {
            
            System.out.println(" "+exc);
            return null;
        }
     }

}
