package com.scone.DeCypher.cipher;

import com.scone.DeCypher.model.CipherType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CipherFactory {
    private final Map<CipherType, Cipher> cipherInstances = new HashMap<>();

    public CipherFactory() {
        cipherInstances.put(CipherType.CAESAR, new CaesarCipher());
        cipherInstances.put(CipherType.VIGENERE, new VigenereCipher());
        cipherInstances.put(CipherType.ATBASH, new AtbashCipher());
    }

    public Cipher getCipher(String cipherName){
        CipherType type = CipherType.fromString(cipherName);
        Cipher cipher = cipherInstances.get(type);
        if(cipher == null) {
            throw new IllegalArgumentException("Cipher not found: " + cipherName);
        }

        return cipher;
    }
}
