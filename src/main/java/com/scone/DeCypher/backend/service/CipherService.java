package com.scone.DeCypher.backend.service;

import com.scone.DeCypher.backend.cipher.CipherFactory;
import com.scone.DeCypher.backend.cipher.EncryptionCipher;
import com.scone.DeCypher.backend.model.ChainedCipherRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public String encryptChained(ChainedCipherRequest request) {
        String result = request.getText();

        for(ChainedCipherRequest.CipherStep step : request.getSteps()) {
            EncryptionCipher cipher = cipherFactory.getCipher(step.getCipher(), step.getKey());
            result = cipher.encrypt(result);
        }

        return result;
    }

    public String decryptChained(ChainedCipherRequest request) {
        String result = request.getText();

        List<ChainedCipherRequest.CipherStep> reversedSteps = request.getSteps();
        Collections.reverse(reversedSteps);

        for(ChainedCipherRequest.CipherStep step : reversedSteps) {
            EncryptionCipher cipher = cipherFactory.getCipher(step.getCipher(), step.getKey());
            result = cipher.decrypt(result);
        }

        return result;
    }
}
