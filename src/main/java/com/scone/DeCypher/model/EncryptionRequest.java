package com.scone.DeCypher.model;

public class EncryptionRequest {
    private String text;
    private String cipher;
    private String key;

    public EncryptionRequest(){

    }

    public EncryptionRequest(String text, String cipher, String key){
        this.text = text;
        this.cipher = cipher;
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
