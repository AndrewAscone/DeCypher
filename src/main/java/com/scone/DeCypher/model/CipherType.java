package com.scone.DeCypher.model;

import java.util.function.BiFunction;

public enum CipherType {
    CAESAR(CipherType::caesarEncrypt, CipherType::caesarDecrypt),
    VIGENERE(CipherType::vigenereEncrypt, CipherType::vigenereDecrypt);

    private final BiFunction<String, Integer, String> encryptFunction;
    private final BiFunction<String, Integer, String> decryptFunction;

    CipherType(BiFunction<String, Integer, String> encryptFunction, BiFunction<String, Integer, String> decryptFunction){
        this.encryptFunction = encryptFunction;
        this.decryptFunction = decryptFunction;
    }

    public String encrypt(String text, int shiftOrKey){
        return encryptFunction.apply(text, shiftOrKey);
    }

    public String decrypt(String text, int shiftOrKey){
        return decryptFunction.apply(text, shiftOrKey);
    }

    //Caesar cipher encrypt/decrypt
    private static String caesarEncrypt(String text, int shift){
        StringBuilder result = new StringBuilder();
        shift = shift % 26;

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

    private static String caesarDecrypt(String text, int shift){
        return caesarEncrypt(text, 26 - (shift % 26));
    }

    //Vigenere cipher encrypt/decrypt
    private static String vigenereEncrypt(String text, int keyAsInt){
        String key = String.valueOf(keyAsInt);
        return processVigenere(text, key, true);
    }

    private static String vigenereDecrypt(String text, int keyAsInt){
        String key = String.valueOf(keyAsInt);
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
