package com.scone.DeCypher.testcipher;

import com.scone.DeCypher.backend.cipher.AtbashCipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAtbashCipher {

    private AtbashCipher cipher;

    @BeforeEach
    void setUp() {
        cipher = new AtbashCipher(); // No key for Atbash cipher
    }

    @Test
    void testEncryptText() {
        // given
        String input = "AbcXYZ";

        // when
        String result = cipher.encrypt(input);

        // then
        assertEquals("ZyxCBA", result);
    }

    @Test
    void testDecryptText() {
        // given
        String encrypted = "ZyxCBA";

        // when
        String result = cipher.decrypt(encrypted);

        // then
        assertEquals("AbcXYZ", result);
    }

    @Test
    void testEncryptWithNonAlphabeticCharacters() {
        // given
        String input = "Hello, World!";

        // when
        String result = cipher.encrypt(input);

        // then
        assertEquals("Svool, Dliow!", result);
    }

    @Test
    void testDecryptWithNonAlphabeticCharacters() {
        // given
        String encrypted = "Svool, Dliow!";

        // when
        String result = cipher.decrypt(encrypted);

        // then
        assertEquals("Hello, World!", result);
    }

    @Test
    void testIsValidKey() {
        // Atbash has no key, should always be true
        assertTrue(cipher.isValidKey());
    }

}
