package com.scone.DeCypher.testcipher;

import com.scone.DeCypher.backend.cipher.VigenereCipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestVigenereCipher {

    private VigenereCipher cipher;
    private final String defaultKey = "key";

    @BeforeEach
    void setUp() {
        cipher = new VigenereCipher(defaultKey);
    }

    @Test
    void testEncryptText() {
        // given
        String input = "HELLOworld";

        // when
        String encrypted = cipher.encrypt(input);

        // then
        assertEquals("RIJVSuyvjn", encrypted);
    }

    @Test
    void testDecryptText() {
        // given
        String encrypted = cipher.encrypt("HELLOworld");

        // when
        String decrypted = cipher.decrypt(encrypted);

        // then
        assertEquals("HELLOworld", decrypted);
    }

    @Test
    void testInputWithNumericCharacters() {
        // given
        String input = "Hello, World! 123";

        // when
        String encrypted = cipher.encrypt(input);
        String decrypted = cipher.decrypt(encrypted);

        // then
        assertEquals(input, decrypted);
    }

    @Test
    void testMixedCaseKey() {
        // given
        cipher = new VigenereCipher("MiXeDcAsE");
        String input = "Testing";

        // when
        String encrypted = cipher.encrypt(input);
        String decrypted = cipher.decrypt(encrypted);

        // then
        assertEquals(input, decrypted);
    }

    @Test
    void testKeyLongerThanText() {
        // given
        // when
        // then
    }

    @Test
    void testKeyShorterThanText() {
        // given
        // when
        // then
    }

    @Test
    void testAlphabeticKeyIsValid() {
        // given
        // when
        // then
    }

    @Test
    void testKeyWithNumbersIsInvalid() {
        // given
        // when
        // then
    }

    @Test
    void testEncryptThrowsForInvalidKey() {
        // given
        // when
        // then
    }

    @Test
    void testDecryptThrowsForInvalidKey() {
        // given
        // when
        // then
    }

}
