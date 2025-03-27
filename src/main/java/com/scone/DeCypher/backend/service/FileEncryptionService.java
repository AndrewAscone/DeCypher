package com.scone.DeCypher.backend.service;

import com.scone.DeCypher.backend.cipher.CipherFactory;
import com.scone.DeCypher.backend.cipher.EncryptionCipher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileEncryptionService {
    private final CipherFactory cipherFactory;

    public FileEncryptionService(CipherFactory cipherFactory){
        this.cipherFactory = cipherFactory;
    }

    public File encryptFile(MultipartFile file, String cipherName, String key) throws IOException{
        return processFile(file, cipherName, key, true);
    }

    public File decryptFile(MultipartFile file, String cipherName, String key) throws IOException{
        return processFile(file, cipherName, key, false);
    }

    public File processFile(MultipartFile file, String cipherName, String key, boolean encrypt) throws IOException{
        EncryptionCipher cipher = cipherFactory.getCipher(cipherName, key);

        // Read the entire file into a byte array
        byte[] fileBytes = file.getBytes();

        // Process the entire file at once
        byte[] processedBytes = encrypt ? cipher.encryptBytes(fileBytes) : cipher.decryptBytes(fileBytes);

        //Create a temp file for output
        String suffix = encrypt ? ".enc" : ".dec";
        Path tempFile = Files.createTempFile("encrypted_", suffix);

        Files.write(tempFile, processedBytes); // Write processed data to the file

        return tempFile.toFile();
    }
}
