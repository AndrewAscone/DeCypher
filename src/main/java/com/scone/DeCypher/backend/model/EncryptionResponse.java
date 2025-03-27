package com.scone.DeCypher.backend.model;

public class EncryptionResponse {
    private String result;
    private String cipher;

    public EncryptionResponse(){

    }

    public EncryptionResponse(String result, String cipher){
        this.result = result;
        this.cipher = cipher;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }
}
