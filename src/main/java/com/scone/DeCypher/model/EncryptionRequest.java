package com.scone.DeCypher.model;

public class EncryptionRequest {
    private String text;
    private String cipher;
    private int shift; // Used with Caesar
    private String key; // Used with Vigenère

    public EncryptionRequest(){

    }

    public EncryptionRequest(String text, String cipher, int shift, String key){
        this.text = text;
        this.cipher = cipher;
        this.shift = shift;
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

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
