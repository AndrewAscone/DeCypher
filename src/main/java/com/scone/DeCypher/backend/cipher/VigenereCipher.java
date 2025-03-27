package com.scone.DeCypher.backend.cipher;

public class VigenereCipher implements EncryptionCipher {
    private String key;

    public VigenereCipher(String key){
        this.key = key;
    }

    @Override
    public String encrypt(String text) {
        if(!isValidKey()){
            throw new IllegalArgumentException("Invalid key for Vigenère cipher. The key must contain only letters.");
        }

        StringBuilder encryptedText = new StringBuilder();
        key = formatKey(text);

        for (int i = 0; i < text.length(); i++) {
            char nextTextChar = text.charAt(i);
            char nextKeyChar = key.charAt(i);

            if(Character.isLetter(nextTextChar)){
                char base = Character.isUpperCase(nextTextChar) ? 'A' : 'a';
                int shift = nextKeyChar - base;
                encryptedText.append((char) ((nextTextChar - base + shift) % 26 + base));
            } else {
                encryptedText.append(nextTextChar);
            }
        }

        return encryptedText.toString();
    }

    @Override
    public String decrypt(String text) {
        if(!isValidKey()){
            throw new IllegalArgumentException("Invalid key for Vigenère cipher. The key must contain only letters.");
        }

        StringBuilder decryptedText = new StringBuilder();
        key = formatKey(text);

        for (int i = 0; i < text.length(); i++) {
            char nextTextChar = text.charAt(i);
            char nextKeyChar = key.charAt(i);

            if(Character.isLetter(nextTextChar)){
                char base = Character.isUpperCase(nextTextChar) ? 'A' : 'a';
                int shift = nextKeyChar - base;
                decryptedText.append((char) ((nextTextChar - base - shift + 26) % 26 + base));
            } else {
                decryptedText.append(nextTextChar);
            }
        }

        return decryptedText.toString();
    }

    @Override
    public boolean isValidKey() {
        return key != null && key.matches("[A-Za-z]+"); // Regex here means 'Only letters, at least one character'
    }

    private String formatKey(String text){
        StringBuilder formattedKey = new StringBuilder();
        int keyLength = key.length();
        int textLength = text.length();

        for (int i = 0, j = 0; i < textLength; i++) {
            char nextChar = text.charAt(i);
            if(Character.isLetter(nextChar)){
                formattedKey.append(key.charAt(j % keyLength));
                j++;
            } else {
                formattedKey.append(nextChar);
            }
        }

        return formattedKey.toString();
    }
}
