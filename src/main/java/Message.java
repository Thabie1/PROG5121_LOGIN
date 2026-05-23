/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;
/**
 *
 * @author thabe
 */
public class Message {
        private String messageId;
    private int messageNumber;
    private String recipientNumber;
    private String messagePayload;
    private String messageHash;

    // Constructor to automatically generate values upon creation
    public Message(int messageNumber, String recipientNumber, String messagePayload) {
        this.messageNumber = messageNumber;
        this.recipientNumber = recipientNumber;
        this.messagePayload = messagePayload;
       
        // Autogenerate a 10-digit Message ID
        this.messageId = generateRandomId();
       
        // Autogenerate the unique Message Hash
        this.messageHash = createMessageHash(this.messageId, this.messageNumber, this.messagePayload);
    }

    // Task: Helper to generate a random 10-digit tracking number
    private String generateRandomId() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Task: Check if message ID is exactly 10 characters long
    public boolean checkMessageId() {
        return this.messageId != null && this.messageId.length() == 10;
    }

    // Task: Verify cell format and country code requirements
    public String checkRecipientCell() {
        if (this.recipientNumber.startsWith("+") && this.recipientNumber.length() == 12) {
// Change "Call" to "Cell
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Task: Handle message payload length rules (Max 250 characters)
    public String checkMessageLength() {
        if (this.messagePayload.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (this.messagePayload.length() - 250) + " character(s); please reduce the size.";
        }
    }

    // Task: Format the uppercase Hash string
    public String createMessageHash(String msgId, int msgNum, String messageText) {
        String idPart = msgId.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
       
        String rawHash = idPart + ":" + msgNum + firstWord + lastWord;
        return rawHash.toUpperCase();
    }

    // Getters for displaying summary records
    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipientNumber() { return recipientNumber; }
    public String getMessagePayload() { return messagePayload; }
}

