package com.scone.DeCypher.cipher;

import com.scone.DeCypher.model.CipherType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class CipherFactory {
    private final Map<CipherType, Function<String, Cipher>> cipherConstructors = new HashMap<>();

    public CipherFactory() {
        cipherConstructors.put(CipherType.CAESAR, CaesarCipher::new);
        cipherConstructors.put(CipherType.VIGENERE, VigenereCipher::new);
        cipherConstructors.put(CipherType.ATBASH, key -> new AtbashCipher());
    }

    public Cipher getCipher(String cipherName, String key){
        CipherType type = CipherType.fromString(cipherName);
        Function<String, Cipher> constructor = cipherConstructors.get(type);

        if(constructor == null) {
            throw new IllegalArgumentException("Cipher not found: " + cipherName);
        }

        return constructor.apply(key);
    }
}
