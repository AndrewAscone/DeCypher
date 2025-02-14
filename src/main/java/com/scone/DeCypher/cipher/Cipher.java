package com.scone.DeCypher.cipher;

public interface Cipher {
    String encrypt(String text, String key);
    String decrypt(String text, String key);
    boolean isValidKey(String key);
}
