package com.scone.DeCypher.controller;

import com.scone.DeCypher.service.FileEncryptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileEncryptionController {
    private final FileEncryptionService fileEncryptionService;

    public FileEncryptionController (FileEncryptionService fileEncryptionService){
        this.fileEncryptionService = fileEncryptionService;
    }

    public ResponseEntity<byte[]> encryptFile(){
        return null;
    }

    public ResponseEntity<byte[]> decryptFile(){
        return null;
    }

    private ResponseEntity<byte[]> processFile(){
        return null;
    }
}
