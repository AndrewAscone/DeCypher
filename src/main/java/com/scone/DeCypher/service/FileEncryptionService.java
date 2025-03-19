package com.scone.DeCypher.service;

import com.scone.DeCypher.cipher.CipherFactory;
import com.scone.DeCypher.cipher.EncryptionCipher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;

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

        //Create a temp file for output
        String suffix = encrypt ? ".enc" : ".dec";
        Path tempFile = Files.createTempFile("encrypted_", suffix);

        try(InputStream inputStream = file.getInputStream();
            OutputStream outputStream = new FileOutputStream(tempFile.toFile())){

            byte[] buffer = new byte[4096];
            int bytesRead;

            while((bytesRead = inputStream.read(buffer)) != -1){
                byte[] processedBytes = encrypt
                        ? cipher.encrypt(Base64.getEncoder().encodeToString(Arrays.copyOf(buffer, bytesRead))).getBytes()
                        : Base64.getDecoder().decode(cipher.decrypt(new String(Arrays.copyOf(buffer, bytesRead), StandardCharsets.UTF_8)));

                outputStream.write(processedBytes);
            }
        }

        return tempFile.toFile();
    }
}
