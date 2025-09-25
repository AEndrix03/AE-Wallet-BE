package it.aredegalli.wallet.security.encryption;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class EncryptionService {

    @Value("${security.crypto.aes-secret-key}")
    private String aesBase64Key;

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public SecretKey getKey() {
        byte[] decodedKey = Base64.getDecoder().decode(aesBase64Key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public String encrypt(String plaintext) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getKey());

            byte[] iv = cipher.getIV();
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            byte[] encryptedWithIv = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, encryptedWithIv, iv.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(encryptedWithIv);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public String decrypt(String encryptedText) {
        try {
            byte[] encryptedWithIv = Base64.getDecoder().decode(encryptedText);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            int ivLength = cipher.getBlockSize();

            byte[] iv = new byte[ivLength];
            System.arraycopy(encryptedWithIv, 0, iv, 0, iv.length);

            byte[] encrypted = new byte[encryptedWithIv.length - ivLength];
            System.arraycopy(encryptedWithIv, ivLength, encrypted, 0, encrypted.length);

            cipher.init(Cipher.DECRYPT_MODE, getKey(), new IvParameterSpec(iv));
            byte[] decryptedBytes = cipher.doFinal(encrypted);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}