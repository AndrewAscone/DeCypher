package com.scone.DeCypher.model;

public class EncryptionRequest {
    private String text;
    private String cipher;
    private int shift;

    public EncryptionRequest(){

    }

    public EncryptionRequest(String text, String cipher, int shift){
        this.text = text;
        this.cipher = cipher;
        this.shift = shift;
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
}
