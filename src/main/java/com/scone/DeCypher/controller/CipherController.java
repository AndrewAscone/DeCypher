package com.scone.DeCypher.controller;

import com.scone.DeCypher.model.EncryptionRequest;
import com.scone.DeCypher.model.EncryptionResponse;
import com.scone.DeCypher.service.CipherService;
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
}
