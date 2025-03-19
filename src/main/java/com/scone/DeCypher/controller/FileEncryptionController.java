package com.scone.DeCypher.controller;

import com.scone.DeCypher.service.FileEncryptionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/file")
public class FileEncryptionController {
    private final FileEncryptionService fileEncryptionService;

    public FileEncryptionController (FileEncryptionService fileEncryptionService){
        this.fileEncryptionService = fileEncryptionService;
    }

    @PostMapping("/encrypt")
    public ResponseEntity<byte[]> encryptFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("cipher") String cipherName,
                                              @RequestParam("key") String key){
        return processFile(file, cipherName, key, true);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<byte[]> decryptFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("cipher") String cipherName,
                                              @RequestParam("key") String key){
        return processFile(file, cipherName, key, false);
    }

    private ResponseEntity<byte[]> processFile(MultipartFile file, String cipherName, String key, boolean encrypt){
        try{
            File processedFile = fileEncryptionService.processFile(file, cipherName, key, encrypt);
            byte[] fileContent = Files.readAllBytes(processedFile.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + processedFile.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("File processing failed: " + e.getMessage()).getBytes());
        }
    }
}
