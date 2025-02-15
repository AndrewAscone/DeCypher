package com.scone.DeCypher.model;

import com.scone.DeCypher.cipher.CaesarCipher;
import com.scone.DeCypher.cipher.Cipher;
import com.scone.DeCypher.cipher.VigenereCipher;

public enum CipherType {
    CAESAR("caesar", CaesarCipher.class),
    VIGENERE("vigenere", VigenereCipher.class);

    private final String name;
    private final Class<? extends Cipher> cipherClass;

    CipherType(String name, Class<? extends Cipher> cipherClass){
        this.name = name;
        this.cipherClass = cipherClass;
    }

    public String getName(){
        return name;
    }

    public Class<? extends Cipher> getCipherClass(){
        return cipherClass;
    }

    public static CipherType fromString(String name){
        for(CipherType type : values()){
            if(type.name.equalsIgnoreCase(name)){
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported cipher type: " + name);
    }
}
