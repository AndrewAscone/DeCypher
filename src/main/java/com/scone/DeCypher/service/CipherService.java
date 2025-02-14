package com.scone.DeCypher.service;

import com.scone.DeCypher.model.CipherType;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;

@Service
public class CipherService {

    public String encrypt(String text, String cipher, int shift, String key){
        try {
            CipherType cipherType = CipherType.valueOf(cipher.toUpperCase());
            return cipherType.encrypt(text, key);
        } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Unsupported cipher type: " + cipher);
        }
    }

    public String decrypt(String text, String cipher, int shift, String key){
        try{
            CipherType cipherType = CipherType.valueOf(cipher.toUpperCase());
            return cipherType.decrypt(text, key);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unsupported cipher type: " + cipher);
        }
    }
}
