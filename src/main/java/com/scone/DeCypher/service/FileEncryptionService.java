package com.scone.DeCypher.service;

import com.scone.DeCypher.cipher.CipherFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    private File processFile(MultipartFile file, String cipherName, String key, boolean encrypt) throws IOException{

    }
}
