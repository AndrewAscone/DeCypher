package com.scone.DeCypher.backend.model;

import java.util.List;

public class ChainedCipherRequest {
    private String text;
    private List<CipherStep> steps;

    public ChainedCipherRequest() {

    }

    public ChainedCipherRequest(String text, List<CipherStep> steps) {
        this.text = text;
        this.steps = steps;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<CipherStep> getSteps() {
        return steps;
    }

    public void setSteps(List<CipherStep> steps) {
        this.steps = steps;
    }

    public static class CipherStep {
        private String cipher;
        private String key;

        public CipherStep() {

        }

        public CipherStep(String cipher, String key) {
            this.cipher = cipher;
            this.key = key;
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
}
