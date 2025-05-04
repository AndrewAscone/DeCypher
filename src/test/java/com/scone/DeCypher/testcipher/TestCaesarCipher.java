package com.scone.DeCypher.testcipher;

import com.scone.DeCypher.backend.cipher.CaesarCipher;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestCaesarCipher {

    @Test
    void testEncryptSimpleShift() {
        // given
        CaesarCipher cipher = new CaesarCipher("3");
        String input = "ABCxyz";

        //when
        String result = cipher.encrypt(input);

        //then
        assertEquals("DEFabc", result);
    }
}
