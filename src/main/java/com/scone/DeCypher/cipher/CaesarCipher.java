package com.scone.DeCypher.cipher;

public class CaesarCipher implements Cipher{
    @Override
    public String encrypt(String text, String key) {
        int shift = Integer.parseInt(key);
        return shiftText(text, shift);
    }

    @Override
    public String decrypt(String text, String key) {
        int shift = Integer.parseInt(key);
        return shiftText(text, -shift);
    }

    @Override
    public boolean isValidKey(String key) {
        try {
            int shift = Integer.parseInt(key);
            return shift >= 0;
        } catch (NumberFormatException e){
            return false;
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
