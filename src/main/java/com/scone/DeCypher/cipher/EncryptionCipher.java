package com.scone.DeCypher.cipher;

public interface EncryptionCipher {
    String encrypt(String text);
    String decrypt(String text);
    boolean isValidKey();
}
