package com.scone.DeCypher.cipher;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AESCipher implements Cipher{
    private final byte[] key;
    private final byte[] iv;

    public AESCipher(String key){
        this.key = validateAndFormatKey(key);
        this.iv = generateIV();
    }

    @Override
    public String encrypt(String text) {
        return "";
    }

    @Override
    public String decrypt(String text) {
        return "";
    }

    @Override
    public boolean isValidKey() {
        return false;
    }

    private byte[] generateIV(){
        byte[] iv = new byte[16]; // AES block size is 16 bytes
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    private byte[] validateAndFormatKey(String keyString){
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        if(keyBytes.length == 16 || keyBytes.length == 24 || keyBytes.length == 32){
            return keyBytes;
        }

        throw new IllegalArgumentException("AES key must be 16, 24, or 32 bytes.");
    }

    private byte[] processData(byte[] input, boolean encrypt){
        return null;
    }
}
