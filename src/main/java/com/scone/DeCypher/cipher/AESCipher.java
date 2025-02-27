package com.scone.DeCypher.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCipher implements EncryptionCipher {
    private final byte[] key;
    private final byte[] iv;

    public AESCipher(String key){
        this.key = validateAndFormatKey(key);
        this.iv = generateIV();
    }

    @Override
    public String encrypt(String text) {
        return processCipher(text, Cipher.ENCRYPT_MODE);
    }

    @Override
    public String decrypt(String text) {
        return processCipher(text, Cipher.DECRYPT_MODE);
    }

    @Override
    public boolean isValidKey() {
        return key.length == 16; // AES-128 requires a 16-byte key
    }

    private byte[] generateIV(){
        byte[] iv = new byte[16]; // CBC mode uses a 16-byte IV
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    private byte[] validateAndFormatKey(String keyString){
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        if(keyBytes.length == 16){
            return keyBytes;
        }

        throw new IllegalArgumentException("AES key must be exactly 16 bytes (128-bit).");
    }

    private String processCipher(String text, int mode){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(mode, secretKeySpec, new IvParameterSpec(iv));

            byte[] outputBytes = (mode == Cipher.ENCRYPT_MODE) ?
                    cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)) :
                    cipher.doFinal(Base64.getDecoder().decode(text));

            return (mode == Cipher.ENCRYPT_MODE) ?
                    Base64.getEncoder().encodeToString(outputBytes) :
                    new String(outputBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES processing failed", e);
        }
    }
}
