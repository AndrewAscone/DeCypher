package com.scone.DeCypher.model;

import java.util.function.BiFunction;

public enum CipherType {
    CAESAR(CipherType::caesarEncrypt, CipherType::caesarDecrypt),
    VIGENERE(CipherType::vigenereEncrypt, CipherType::vigenereDecrypt);

    private final BiFunction<String, String, String> encryptFunction;
    private final BiFunction<String, String, String> decryptFunction;

    CipherType(BiFunction<String, String, String> encryptFunction, BiFunction<String, String, String> decryptFunction){
        this.encryptFunction = encryptFunction;
        this.decryptFunction = decryptFunction;
    }

    public String encrypt(String text, String key){
        return encryptFunction.apply(text, key);
    }

    public String decrypt(String text, String key){
        return decryptFunction.apply(text, key);
    }

    //Caesar cipher encrypt/decrypt
    private static String caesarEncrypt(String text, String shiftString){
        StringBuilder result = new StringBuilder();
        int shift = Integer.parseInt(shiftString);

        for(char ch : text.toCharArray()){
            if(Character.isLetter(ch)){
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift) % 26 + base));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    private static String caesarDecrypt(String text, String shiftString){
        int shift = Integer.parseInt(shiftString);
        return caesarEncrypt(text, String.valueOf(26 - (shift % 26)));
    }

    //Vigenere cipher encrypt/decrypt
    private static String vigenereEncrypt(String text, String key){
        return processVigenere(text, key, true);
    }

    private static String vigenereDecrypt(String text, String key){
        return processVigenere(text, key, false);
    }

    private static String processVigenere(String text, String key, boolean encrypt){
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for(char ch : text.toCharArray()){
            if(Character.isLetter(ch)){
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int shift = key.charAt(keyIndex % key.length()) - 'A';
                if(!encrypt) shift = -shift;
                result.append((char) ((ch - base + shift + 26) % 26 + base));
                keyIndex++;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
