package com.scone.DeCypher.backend.model;

import com.scone.DeCypher.backend.cipher.*;

public enum CipherType {
    CAESAR("caesar", CaesarCipher.class),
    VIGENERE("vigenere", VigenereCipher.class),
    ATBASH("atbash", AtbashCipher.class),
    AES("AES", AESCipher.class);

    private final String name;
    private final Class<? extends EncryptionCipher> cipherClass;

    CipherType(String name, Class<? extends EncryptionCipher> cipherClass){
        this.name = name;
        this.cipherClass = cipherClass;
    }

    public String getName(){
        return name;
    }

    public Class<? extends EncryptionCipher> getCipherClass(){
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
