package com.scone.DeCypher.testservice;

import com.scone.DeCypher.backend.cipher.CipherFactory;
import com.scone.DeCypher.backend.model.ChainedCipherRequest;
import com.scone.DeCypher.backend.model.ChainedCipherRequest.CipherStep;
import com.scone.DeCypher.backend.service.CipherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCipherServiceChained {
    private CipherService cipherService;

    @BeforeEach
    void setUp(){
        CipherFactory factory = new CipherFactory();
        cipherService = new CipherService(factory);
    }

    @Test
    void testChainedEncryptAndDecrypt() {
        String input = "Hello World!";
        List<ChainedCipherRequest.CipherStep> steps = List.of(
                new CipherStep("Caesar", "3"),
                new CipherStep("Vigenere", "KEY"),
                new CipherStep("Atbash", null)
        );
        ChainedCipherRequest request = new ChainedCipherRequest(input, steps);

        String encrypted = cipherService.encryptChained(request);
        String decrypted = cipherService.decryptChained(new ChainedCipherRequest(encrypted, steps));

        assertNotEquals(input, encrypted, "Encrypted text should differ from input");
        assertEquals(input, decrypted, "Decryption should return the original input");
    }

    @Test
    void testSingleStepCaesar(){

    }

    @Test
    void testMultipleStepsCaesarAtbash(){

    }

    @Test
    void testOrderMatters(){

    }

    @Test
    void testEmptyStepsReturnsOriginalText(){

    }
}
