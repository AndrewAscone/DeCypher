package com.scone.DeCypher.cipher;

public interface Cipher {
    String encrypt(String text);
    String decrypt(String text);
    boolean isValidKey();
}
