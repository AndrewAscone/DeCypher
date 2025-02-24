package com.scone.DeCypher.cipher;

public class AtbashCipher implements Cipher{
    @Override
    public String encrypt(String text, String key) {
        return transform(text);
    }

    @Override
    public String decrypt(String text, String key) {
        return transform(text); // Atbash is self-inverse, encryption and decryption are identical
    }

    @Override
    public boolean isValidKey(String key) {
        return true; // No key required for Atbash
    }

    public String transform(String text){
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if(Character.isLetter(c)){
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char transformed = (char) (base + ('Z' - Character.toUpperCase(c))); // Reverses alphabetical position
                result.append(Character.isUpperCase(c) ? transformed : Character.toLowerCase(transformed));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
