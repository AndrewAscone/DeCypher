package com.scone.DeCypher.util;

import com.scone.DeCypher.model.CipherType;

public class CipherKeyValidator {

    public static String validateKey(CipherType cipherType, String key){
        if(cipherType == CipherType.CAESAR) {
            return validateCaesarKey(key);
        } else if (cipherType == CipherType.VIGENERE) {
            return validateVigenereKey(key);
        }
        throw new IllegalArgumentException("Unsupported cipher type: " + cipherType);
    }

    private static String validateCaesarKey(String key){
        try{
            Integer.parseInt(key);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Caesar cipher requires a numeric shift.");
        }
        return key;
    }

    private static String validateVigenereKey(String key){
        if (key == null || key.isEmpty() || !key.matches("[A-Za-z]+")){
            throw new IllegalArgumentException("Vigenère cipher requires a non-empty alphabetic key.");
        }
        return key.toUpperCase();
    }
}
