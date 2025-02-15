package com.scone.DeCypher.cipher;

public class VigenereCipher implements Cipher{
    @Override
    public String encrypt(String text, String key) {
        return "";
    }

    @Override
    public String decrypt(String text, String key) {
        return "";
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
