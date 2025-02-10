package com.scone.DeCypher.model;

public class EncryptionRequest {
    private String text;
    private String cipher;

    public EncryptionRequest(){

    }

    public EncryptionRequest(String text, String cipher){
        this.text = text;
        this.cipher = cipher;
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
}
