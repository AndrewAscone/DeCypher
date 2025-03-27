package com.scone.DeCypher.backend.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCipher implements EncryptionCipher {
    private final byte[] key;

    public AESCipher(String key){
        this.key = validateAndFormatKey(key);
    }

    @Override
    public String encrypt(String text) {
        byte[] iv = generateIV(); // Generate new IV for each encryption
        byte[] encryptedData = processCipher(text.getBytes(StandardCharsets.UTF_8), Cipher.ENCRYPT_MODE, iv);

        //Concatenate IV + encrypted data and encode in Base64
        byte[] combined = new byte[iv.length + encryptedData.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedData, 0, combined, iv.length, encryptedData.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    @Override
    public String decrypt(String text) {
        byte[] decodedBytes = Base64.getDecoder().decode(text);

        // Extract IV (first 16 bytes) and Ciphertext (remaining bytes)
        byte[] iv = new byte[16];
        byte[] encryptedData = new byte[decodedBytes.length - 16];

        System.arraycopy(decodedBytes, 0, iv, 0, 16);
        System.arraycopy(decodedBytes, 16, encryptedData, 0, encryptedData.length);

        byte[] decryptedBytes = processCipher(encryptedData, Cipher.DECRYPT_MODE, iv);
        return new String(decryptedBytes, StandardCharsets.UTF_8); // Convert bytes back to string
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

    @Override
    public byte[] encryptBytes(byte[] data){
        byte[] iv = generateIV();
        byte[] encryptedData = processCipher(data, Cipher.ENCRYPT_MODE, iv);

        // Prepend IV to the encrypted data
        byte[] combined = new byte[iv.length + encryptedData.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedData, 0, combined, iv.length, encryptedData.length);

        return combined;
    }

    @Override
    public byte[] decryptBytes(byte[] data) {
        if(data.length < 16) {
            throw new IllegalArgumentException("Invalid AES encrypted data: missing IV");
        }

        // Extract IV (first 16 bytes) and encrypted content
        byte[] iv = new byte[16];
        byte[] encryptedData = new byte[data.length - 16];

        System.arraycopy(data, 0, iv, 0, 16);
        System.arraycopy(data, 16, encryptedData, 0, encryptedData.length);

        return processCipher(encryptedData, Cipher.DECRYPT_MODE, iv);
    }

    private byte[] validateAndFormatKey(String keyString){
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        if(keyBytes.length == 16){
            return keyBytes;
        }

        throw new IllegalArgumentException("AES key must be exactly 16 bytes (128-bit).");
    }

    private byte[] processCipher(byte[] inputBytes, int mode, byte[] iv){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(mode, secretKeySpec, new IvParameterSpec(iv));

            return cipher.doFinal(inputBytes);
        } catch (Exception e) {
            throw new RuntimeException("AES processing failed", e);
        }
    }
}
