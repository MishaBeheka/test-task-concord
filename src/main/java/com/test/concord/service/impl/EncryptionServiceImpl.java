package com.test.concord.service.impl;

import com.test.concord.service.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;


@Slf4j
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private static final String SECRET_KEY = "my_super_secret_key";
    private static final String SALT = "ssshhhhhhhhhhh!!!!";


    @Override
    public Optional<String> encrypt(String inputData) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            String result = Base64.getEncoder().encodeToString(cipher.doFinal(inputData.getBytes(StandardCharsets.UTF_8)));
            return Optional.of(result);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> decrypt(String inputData) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            String result = new String(cipher.doFinal(Base64.getDecoder().decode(inputData)));
            return Optional.of(result);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return Optional.empty();
    }
}
