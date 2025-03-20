package com.scone.DeCypher.cipher;

public interface EncryptionCipher {
    String encrypt(String text);
    String decrypt(String text);
    boolean isValidKey();

    default byte[] encryptBytes(byte[] data){
        throw new UnsupportedOperationException("Byte encryption not supported for this cipher.");
    }
    default byte[] decryptBytes(byte[] data){
        throw new UnsupportedOperationException("Byte decryption not supported for this cipher.");
    }
}
