package com.scone.DeCypher.testcipher;

import com.scone.DeCypher.backend.cipher.CaesarCipher;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestCaesarCipher {

    @Test
    void testEncryptSmallShift() {
        // given
        CaesarCipher cipher = new CaesarCipher("3");
        String input = "ABCxyz";

        //when
        String result = cipher.encrypt(input);

        //then
        assertEquals("DEFabc", result);
    }

    @Test
    void testDecryptSmallShift(){
        // given
        CaesarCipher cipher = new CaesarCipher("3");
        String encryptedInput = "DEFabc";

        // when
        String result = cipher.decrypt(encryptedInput);

        // then
        assertEquals("ABCxyz", result);
    }

    @Test
    void testHandleLargeShiftValue() {
        // given
        CaesarCipher cipher = new CaesarCipher("1500");
        String original = "TestingCaesarCipher123!";

        // when
        String encrypted = cipher.encrypt(original);
        String result = cipher.decrypt(encrypted);

        // then
        assertEquals(original, result);
    }

    @Test
    void testNonLettersCharactersRemainUnchanged() {
        // given
        CaesarCipher cipher = new CaesarCipher("5");
        String original = "Hello, World! 123";

        // when
        String encrypted = cipher.encrypt(original);

        // then
        assertEquals("Mjqqt, Btwqi! 123", encrypted);
    }

    @Test
    void testKeyIsValid() {
        // given
        CaesarCipher cipher = new CaesarCipher("42");

        // when
        boolean isValid = cipher.isValidKey();

        // then
        assertTrue(isValid);
    }

    @Test
    void testInvalidKey() {
        // given
        CaesarCipher cipher = new CaesarCipher("not-a-number");

        // when
        boolean isValid = cipher.isValidKey();

        // then
        assertFalse(isValid);
    }

    @Test
    void testEncryptThrowsForInvalidKey() {
        // given
        CaesarCipher cipher = new CaesarCipher("badKey");

        // when/then
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt("text"));
    }

    @Test
    void testDecryptThrowsForNegativeKey() {
        // given
        CaesarCipher cipher = new CaesarCipher("-5");

        // when/then
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt("text"));
    }
}
