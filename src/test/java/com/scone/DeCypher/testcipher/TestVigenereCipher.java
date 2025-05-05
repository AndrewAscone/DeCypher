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
        cipher = new VigenereCipher("ThisIsAVeryLongKey");
        String input = "Short";

        // when
        String encrypted = cipher.encrypt(input);
        String decrypted = cipher.decrypt(encrypted);

        // then
        assertEquals(input, decrypted);
    }

    @Test
    void testKeyShorterThanText() {
        // given
        cipher = new VigenereCipher("abc");
        String input = "ThisIsALongerText";

        // when
        String encrypted = cipher.encrypt(input);
        String decrypted = cipher.decrypt(encrypted);

        // then
        assertEquals(input, decrypted);
    }

    @Test
    void testAlphabeticKeyIsValid() {
        // given
        cipher = new VigenereCipher("NewKey");

        // when
        boolean isValid = cipher.isValidKey();

        // then
        assertTrue(isValid);
    }

    @Test
    void testKeyWithNumbersIsInvalid() {
        // given
        cipher = new VigenereCipher("sh1ft");

        // when
        boolean isValid = cipher.isValidKey();

        // then
        assertFalse(isValid);
    }

    @Test
    void testEncryptThrowsForInvalidKey() {
        // given
        cipher = new VigenereCipher("!@#");

        // when/then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt("Hello"));
    }

    @Test
    void testDecryptThrowsForInvalidKey() {
        // given
        cipher = new VigenereCipher("123");

        // when/then
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt("Hello"));
    }

}
