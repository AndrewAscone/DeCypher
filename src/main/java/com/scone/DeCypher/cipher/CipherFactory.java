package com.scone.DeCypher.cipher;

import com.scone.DeCypher.model.CipherType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CipherFactory {
    private final Map<CipherType, Cipher> cipherInstance = new HashMap<>();
}
