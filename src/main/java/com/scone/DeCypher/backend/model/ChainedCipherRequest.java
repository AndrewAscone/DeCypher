package com.scone.DeCypher.backend.model;

import java.util.List;

public class ChainedCipherRequest {
    private String text;
    private List<CipherStep> steps;

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
