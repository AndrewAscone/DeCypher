package com.scone.DeCypher.cipher;

public class VigenereCipher implements Cipher{
    @Override
    public String encrypt(String text, String key) {
        if(!isValidKey(key)){
            throw new IllegalArgumentException("Invalid key for Vigenère cipher. The key must contain only letters.");
        }

        StringBuilder encryptedText = new StringBuilder();
        key = formatKey(text, key);

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
    public String decrypt(String text, String key) {
        if(!isValidKey(key)){
            throw new IllegalArgumentException("Invalid key for Vigenère cipher. The key must contain only letters.");
        }

        StringBuilder decryptedText = new StringBuilder();
        key = formatKey(text, key);

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
    public boolean isValidKey(String key) {
        return key != null && key.matches("[A-Za-z]+"); // Regex here means 'Only letters, at least one character'
    }

    private String formatKey(String text, String key){
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
