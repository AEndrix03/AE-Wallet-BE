package com.aendrix.aewallet.services.security;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AESCryptoService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int IV_SIZE = 12;
    private static final int TAG_SIZE = 128;

    private static String secretKey = null;

    public String generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public String encrypt(String plainText, String encodedKey) throws Exception {
        byte[] key = Base64.getDecoder().decode(encodedKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameterSpec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        byte[] cipherTextWithIv = new byte[IV_SIZE + cipherText.length];
        System.arraycopy(iv, 0, cipherTextWithIv, 0, IV_SIZE);
        System.arraycopy(cipherText, 0, cipherTextWithIv, IV_SIZE, cipherText.length);

        return Base64.getEncoder().encodeToString(cipherTextWithIv);
    }

    public String decrypt(String cipherText, String encodedKey) throws Exception {
        byte[] key = Base64.getDecoder().decode(encodedKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

        byte[] cipherTextWithIv = Base64.getDecoder().decode(cipherText);
        byte[] iv = new byte[IV_SIZE];
        byte[] actualCipherText = new byte[cipherTextWithIv.length - IV_SIZE];

        System.arraycopy(cipherTextWithIv, 0, iv, 0, IV_SIZE);
        System.arraycopy(cipherTextWithIv, IV_SIZE, actualCipherText, 0, actualCipherText.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameterSpec);

        byte[] plainText = cipher.doFinal(actualCipherText);
        return new String(plainText);
    }

    public String getKeyFromDockerSecret() {
        if (secretKey == null) {
            try {
                secretKey = new String(Files.readAllBytes(Paths.get("/run/secrets/encryption_key"))).substring(0, 32);
            } catch (IOException | NullPointerException e) {
                throw new RuntimeException(e);
            }
        }
        return secretKey;
    }
}
