/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author thabe
 */
public class MessageTest {
    
   // --- TASK 1: TEST MESSAGE LENGTH (MAX 250 CHARACTERS) ---
   
    @Test
    public void testMessageLengthSuccess() {
        // "Hi Mike, can you join us for dinner tonight?" is well under 250 characters
        Message msg = new Message(0, "+27711234567", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Create a string that intentionally exceeds 250 characters
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMessage.append("a");
        }
       
        Message msg = new Message(0, "+27711234567", longMessage.toString());
        String result = msg.checkMessageLength();
       
        // It should fail and contain the failure warning phrase
        assertTrue(result.contains("Message exceeds 250 characters"));
    }

    // --- TASK 2: TEST RECIPIENT PHONE NUMBER FORMAT ---

    @Test
    public void testRecipientCellSuccess() {
        // Correct format: starts with '+' and is 12 characters long
        Message msg = new Message(0, "+27711234567", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        // Incorrect format: missing the '+' country code (e.g., 0711234567)
        Message msg = new Message(0, "0711234567", "Hello");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // --- TASK 3: TEST MESSAGE HASH GENERATION ---

    @Test
    public void testMessageHashCorrect() {
        // We look for the exact expected format pattern: ID_PART:NUM_FIRST_WORD_LAST_WORD (ALL CAPS)
        String testId = "0312345678";
        int testNum = 0;
        String testPayload = "Hi Mike, can you join us for dinner tonight?";
       
        // Create a dummy message object to call the method
        Message msg = new Message(testNum, "+27711234567", testPayload);
       
        String generatedHash = msg.createMessageHash(testId, testNum, testPayload);
       
        // Expected: "03" + ":" + "0" + "HI" + "TONIGHT?" -> "03:0HITONIGHT?"
        assertEquals("03:0HITONIGHT?", generatedHash);
    }
    
}
