package com.scone.DeCypher.testcipher;

import com.scone.DeCypher.backend.cipher.AESCipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAESCipher {

    private AESCipher cipher;
    private final String key = "thisisasecretkey"; // 16 bytes for AES-128

    @BeforeEach
    void setUp() {
        cipher = new AESCipher(key);
    }

    @Test
    void testEncryptAndDecryptText() {
        // given
        String original = "ConfidentialMessage123";

        // when
        String encrypted = cipher.encrypt(original);
        String decrypted = cipher.decrypt(encrypted);

        // then
        assertEquals(original, decrypted, "Decryption should return the original text");
    }

    @Test
    void testWrongKeyReturnsIncorrectData() {
        // given
        String original = "SensitiveInfo";
        String encrypted = cipher.encrypt(original);
        AESCipher wrongCipher = new AESCipher("wrongsecretkey01");

        // when
        String decrypted = wrongCipher.decrypt(encrypted);

        // then
        assertNotEquals(original, decrypted, "Decryption with wrong key should fail or return incorrect data");
    }

    @Test
    void testEncryptAndDecryptBytes() {
        // given
        byte[] original = "Some binary \u0001\u0002 data".getBytes();

        // when
        byte[] encrypted = cipher.encryptBytes(original);
        byte[] decrypted = cipher.decryptBytes(encrypted);

        // then
        assertArrayEquals(original, decrypted, "Decrypted bytes should match original bytes");
    }

    @Test
    void testProperLengthKeyIsValid() {
        assertTrue(cipher.isValidKey());
    }

    @Test
    void testShortKeyIsNotValid() {
        AESCipher shortKeyCipher = new AESCipher("shortkey1234567");
        assertFalse(shortKeyCipher.isValidKey());
    }

    @Test
    void testShortByteInputThrowsException() {
        // given
        byte[] invalidInput = new byte[10]; // less than 16 bytes (missing IV)

        // then
        assertThrows(IllegalArgumentException.class, () -> cipher.decryptBytes(invalidInput),
                "Should throw when IV is missing from encrypted data");
    }
}
