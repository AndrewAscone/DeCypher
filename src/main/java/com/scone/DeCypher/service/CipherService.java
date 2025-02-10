package com.scone.DeCypher.service;

import org.springframework.stereotype.Service;

@Service
public class CipherService {

    public String encrypt(String text, String cipher){
        //Placeholder logic
        return new StringBuilder(text).reverse().toString();
    }

    public String decrypt(String text, String cipher){
        //Placeholder logic
        return new StringBuilder(text).reverse().toString();
    }
}
