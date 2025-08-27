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
        ChainedCipherRequest request = new ChainedCipherRequest("HELLO",
                List.of(new CipherStep("CAESAR", "3")));

        String encrypted = cipherService.encryptChained(request);
        String decrypted = cipherService.decryptChained(new ChainedCipherRequest(encrypted, request.getSteps()));

        assertEquals("HELLO", decrypted);
    }

    @Test
    void testMultipleStepsCaesarAtbash(){
        ChainedCipherRequest request = new ChainedCipherRequest("HELLO", List.of(
                new CipherStep("CAESAR", "3"),
                new CipherStep("ATBASH", "")
        ));

        String encrypted = cipherService.encryptChained(request);
        String decrypted = cipherService.decryptChained(new ChainedCipherRequest(encrypted, request.getSteps()));

        assertEquals("HELLO", decrypted);
    }

    @Test
    void testOrderMatters(){
        ChainedCipherRequest request1 = new ChainedCipherRequest("WORLD", List.of(
                new CipherStep("CAESAR", "5"),
                new CipherStep("ATBASH", "")
        ));

        String encrypted1 = cipherService.encryptChained(request1);

        ChainedCipherRequest request2 = new ChainedCipherRequest("WORLD", List.of(
                new CipherStep("ATBASH", ""),
                new CipherStep("CAESAR", "5")
        ));

        String encrypted2 = cipherService.encryptChained(request2);

        assertNotEquals(encrypted1, encrypted2, "Changing cipher order should change output");
    }

    @Test
    void testEmptyStepsReturnsOriginalText(){
        ChainedCipherRequest request = new ChainedCipherRequest("HELLO", List.of());

        String encrypted = cipherService.encryptChained(request);
        String decrypted = cipherService.decryptChained(new ChainedCipherRequest(encrypted, request.getSteps()));

        assertEquals("HELLO", encrypted);
        assertEquals("HELLO", decrypted);
    }
}
