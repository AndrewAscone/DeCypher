package com.scone.DeCypher.backend.service;

import com.scone.DeCypher.backend.cipher.CipherFactory;
import org.springframework.stereotype.Service;

@Service
public class CipherService {

    private final CipherFactory cipherFactory;

    public CipherService(CipherFactory cipherFactory){
        this.cipherFactory = cipherFactory;
    }

    public String encrypt(String text, String cipher, String key){
        return cipherFactory.getCipher(cipher, key).encrypt(text);
    }

    public String decrypt(String text, String cipher, String key){
        return cipherFactory.getCipher(cipher, key).decrypt(text);
    }
}
