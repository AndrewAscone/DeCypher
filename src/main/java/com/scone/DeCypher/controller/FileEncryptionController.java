package com.scone.DeCypher.controller;

import com.scone.DeCypher.service.FileEncryptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileEncryptionController {
    private final FileEncryptionService fileEncryptionService;

    public FileEncryptionController (FileEncryptionService fileEncryptionService){
        this.fileEncryptionService = fileEncryptionService;
    }

    public ResponseEntity<byte[]> encryptFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("cipher") String cipherName,
                                              @RequestParam("key") String key){
        return processFile(file, cipherName, key, true);
    }

    public ResponseEntity<byte[]> decryptFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("cipher") String cipherName,
                                              @RequestParam("key") String key){
        return processFile(file, cipherName, key, false);
    }

    private ResponseEntity<byte[]> processFile(MultipartFile file, String cipherName, String key, boolean encrypt){
        return null;
    }
}
