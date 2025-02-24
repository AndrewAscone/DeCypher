package com.scone.DeCypher.service;

import com.scone.DeCypher.cipher.CipherFactory;
import com.scone.DeCypher.model.CipherType;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;

@Service
public class CipherService {

    private final CipherFactory cipherFactory;

    public CipherService(CipherFactory cipherFactory){
        this.cipherFactory = cipherFactory;
    }

    public String encrypt(String text, String cipher, String key){
        return cipherFactory.getCipher(cipher).encrypt(text, key);
    }

    public String decrypt(String text, String cipher, String key){
        return cipherFactory.getCipher(cipher).decrypt(text, key);
    }
}
