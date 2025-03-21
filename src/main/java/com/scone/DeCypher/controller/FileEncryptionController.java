package com.scone.DeCypher.controller;

import com.scone.DeCypher.service.FileEncryptionService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileEncryptionController {
    private final FileEncryptionService fileEncryptionService;

    public FileEncryptionController (FileEncryptionService fileEncryptionService){
        this.fileEncryptionService = fileEncryptionService;
    }

    @PostMapping("/encrypt")
    public ResponseEntity<Resource> encryptFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam("cipher") String cipherName,
                                                @RequestParam("key") String key) throws IOException {
        return processAndRespond(file, cipherName, key, true);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Resource> decryptFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("cipher") String cipherName,
                                              @RequestParam("key") String key) throws IOException {
        return processAndRespond(file, cipherName, key, false);
    }

    private ResponseEntity<Resource> processAndRespond(MultipartFile file, String cipherName, String key, boolean encrypt) throws IOException {
        File processedFile = fileEncryptionService.processFile(file, cipherName, key, encrypt);
        String prefix = encrypt ? "encrypted_" : "decrypted_";
        return buildFileResponse(processedFile, prefix + file.getOriginalFilename());
    }

    private ResponseEntity<Resource> buildFileResponse(File file, String outputFileName) {
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + outputFileName + "\"")
                .body(resource);
    }
}
