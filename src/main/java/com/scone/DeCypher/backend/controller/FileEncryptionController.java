package com.scone.DeCypher.backend.controller;

import com.scone.DeCypher.backend.service.FileEncryptionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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

        ResponseEntity<Resource> response = buildFileResponse(processedFile, prefix + file.getOriginalFilename());

        // Delete file after response
        if(!processedFile.delete()) {
            System.err.println("Warning: Temporary file " + processedFile.getAbsolutePath() + " could not be deleted.");
        }

        return response;
    }

    private ResponseEntity<Resource> buildFileResponse(File file, String outputFileName) throws IOException {
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        // Log file creation
        System.out.println("Temp file created: " + file.getAbsolutePath());

        ResponseEntity<Resource> response = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + outputFileName + "\"")
                .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
                .body(resource);

        // Delete the temp file AFTER response is sent
        new Thread(() -> {
            try{
                Thread.sleep(5000); // Small delay to ensure response is sent
                if(file.delete()){
                    System.out.println("Temp file deleted: " + file.getAbsolutePath());
                } else {
                    // This message is getting sent every time, maybe change sleep time to fix?
                    System.err.println("Warning: Temporary file " + file.getAbsolutePath() + " could not be deleted.");
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }).start();

        return response;
    }
}
