package com.scone.DeCypher.service;

import org.springframework.stereotype.Service;

@Service
public class CipherService {

    public String encrypt(String text, String cipher, int shift){
        if("caesar".equalsIgnoreCase(cipher)){
            return caesarEncrypt(text, shift);
        }

        return text;
    }

    public String decrypt(String text, String cipher, int shift){
        if("caesar".equalsIgnoreCase(cipher)){
            return caesarDecrypt(text, shift);
        }

        return text;
    }

    private String caesarEncrypt(String text, int shift){
        StringBuilder result = new StringBuilder();
        shift = shift % 26;

        for(char ch : text.toCharArray()){
            if(Character.isLetter(ch)){
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char)((ch - base + shift) % 26 + base));
            } else{
              result.append(ch);
            }
        }

        return result.toString();
    }

    private String caesarDecrypt(String text, int shift){
        return caesarEncrypt(text, 26 - (shift % 26));
    }
}
