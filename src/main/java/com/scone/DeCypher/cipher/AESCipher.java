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

    private byte[] processData(byte[] input, boolean encrypt){
        return null;
    }
}
