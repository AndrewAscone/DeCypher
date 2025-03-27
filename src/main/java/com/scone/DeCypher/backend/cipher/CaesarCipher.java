package com.scone.DeCypher.backend.cipher;

public class CaesarCipher implements EncryptionCipher {
    private String key;

    public CaesarCipher(String key){
        this.key = key;
    }

    @Override
    public String encrypt(String text) {
        if(!isValidKey()){
            throw new IllegalArgumentException("Invalid key for Caesar cipher. Key must be a non-negative integer.");
        }

        int shift = Integer.parseInt(key);
        return shiftText(text, shift);
    }

    @Override
    public String decrypt(String text) {
        if(!isValidKey()){
            throw new IllegalArgumentException("Invalid key for Caesar cipher. Key must be a non-negative integer.");
        }

        int shift = Integer.parseInt(key);
        return shiftText(text, -shift);
    }

    @Override
    public boolean isValidKey() {
        try {
            int shift = Integer.parseInt(key);
            return shift >= 0; // Ensure key is a non-negative integer
        } catch (NumberFormatException e){
            return false; // Key is not a valid integer
        }
    }

    private String shiftText(String text, int shift){
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()){
            if(Character.isLetter(c)){
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base + shift + 26) % 26 + base));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
