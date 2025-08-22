package com.scone.DeCypher.backend.controller;

import com.scone.DeCypher.backend.model.ChainedCipherRequest;
import com.scone.DeCypher.backend.model.EncryptionRequest;
import com.scone.DeCypher.backend.model.EncryptionResponse;
import com.scone.DeCypher.backend.service.CipherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CipherController {

    private final CipherService cipherService;

    public CipherController(CipherService cipherService){
        this.cipherService = cipherService;
    }

    @PostMapping("/encrypt")
    public ResponseEntity<EncryptionResponse> encrypt(@RequestBody EncryptionRequest request){
        String result = cipherService.encrypt(request.getText(), request.getCipher(), request.getKey());
        return ResponseEntity.ok(new EncryptionResponse(result, request.getCipher()));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<EncryptionResponse> decrypt(@RequestBody EncryptionRequest request){
        String result = cipherService.decrypt(request.getText(), request.getCipher(), request.getKey());
        return ResponseEntity.ok(new EncryptionResponse(result, request.getCipher()));
    }

    @PostMapping("/encrypt-chained")
    public ResponseEntity<String> encryptChained(@RequestBody ChainedCipherRequest request) {
        String result = cipherService.encryptChained(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/decrypt-chained")
    public ResponseEntity<String> decryptChained(@RequestBody ChainedCipherRequest request) {
        String result = cipherService.decryptChained(request);
        return ResponseEntity.ok(result);
    }
}
