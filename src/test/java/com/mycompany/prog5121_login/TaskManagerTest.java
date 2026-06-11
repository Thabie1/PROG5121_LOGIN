package com.mycompany.prog5121_login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
   
    private TaskManager manager;

    @BeforeEach
    public void setUp() {
        manager = new TaskManager();
        manager.populateTestData();
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertEquals(5, manager.getTaskCount());
    }

    @Test
    public void testGetLongestStoredMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, manager.getLongestStoredMessage());
    }

    @Test
    public void testSearchByMessageId() {
        String expected = "Recipient: 0838884567, Message: It is dinner time !";
        assertEquals(expected, manager.searchByMessageId("MSG_04"));
    }

    @Test
    public void testSearchByRecipient() {
        String expected = "Ok, I am leaving without you.";
        assertEquals(expected, manager.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteMessageByHash() {
        assertTrue(manager.deleteMessageByHash("HASH_B"));
        assertEquals(4, manager.getTaskCount());
    }
}