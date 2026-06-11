package com.mycompany.prog5121_login;

public class TaskManager {
    // The 5 Parallel Arrays required by the task specifications
    private String[] sentMessages = new String[100];
    private String[] disregardedMessages = new String[100];
    private String[] storedMessages = new String[100];
    private String[] messageHashes = new String[100];
    private String[] messageIds = new String[100];
   
    // Additional tracks for complete data reporting
    private String[] recipients = new String[100];
    private String[] flags = new String[100];
   
    private int taskCount = 0;

    public int getTaskCount() {
        return this.taskCount;
    }

    // Adds a record to our parallel positions
    public void addMessage(String recipient, String messageText, String flag, String messageId, String messageHash) {
        recipients[taskCount] = recipient;
        flags[taskCount] = flag;
        messageIds[taskCount] = messageId;
        messageHashes[taskCount] = messageHash;
       
        if (flag.equalsIgnoreCase("Sent")) {
            sentMessages[taskCount] = messageText;
            disregardedMessages[taskCount] = "";
            storedMessages[taskCount] = "";
        } else if (flag.equalsIgnoreCase("Stored")) {
            sentMessages[taskCount] = "";
            disregardedMessages[taskCount] = "";
            storedMessages[taskCount] = messageText;
        } else if (flag.equalsIgnoreCase("Disregard")) {
            sentMessages[taskCount] = "";
            disregardedMessages[taskCount] = messageText;
            storedMessages[taskCount] = "";
        }
        taskCount++;
    }

    // Auto-populates the required assessment tracking logs
    public void populateTestData() {
        taskCount = 0;
        addMessage("+27834557896", "Did you get the cake?", "Sent", "MSG_01", "HASH_A");
        addMessage("+2703884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "MSG_02", "HASH_B");
        addMessage("+27834484567", "Yohoooo, I am at your gate.", "Disregard", "MSG_03", "HASH_C");
        addMessage("0838884567", "It is dinner time !", "Sent", "MSG_04", "HASH_D");
        addMessage("+27838884567", "Ok, I am leaving without you.", "Stored", "MSG_05", "HASH_E");
    }
    // a. Display the sender and recipient of all stored messages
    public String displayStoredMessages() {
        String result = "";
        for (int i = 0; i < taskCount; i++) {
            if (flags[i].equalsIgnoreCase("Stored")) {
                result += "Recipient: " + recipients[i] + " -> Message: " + storedMessages[i] + "\n";
            }
        }
        return result.isEmpty() ? "No stored messages found." : result;
    }

    // b. Display the longest stored message
    public String getLongestStoredMessage() {
        String longest = "";
        for (int i = 0; i < taskCount; i++) {
            if (flags[i].equalsIgnoreCase("Stored") && storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }
        return longest;
    }

    // c. Search for a message ID and return recipient and message
    public String searchByMessageId(String id) {
        for (int i = 0; i < taskCount; i++) {
            if (messageIds[i] != null && messageIds[i].equalsIgnoreCase(id)) {
                String msg = !sentMessages[i].isEmpty() ? sentMessages[i] :
                             (!storedMessages[i].isEmpty() ? storedMessages[i] : disregardedMessages[i]);
                return "Recipient: " + recipients[i] + ", Message: " + msg;
            }
        }
        return "Message ID not found.";
    }

    // d. Search for all messages sent/stored for a particular recipient
    public String searchByRecipient(String targetRecipient) {
        String result = "";
        for (int i = 0; i < taskCount; i++) {
            if (recipients[i] != null && recipients[i].equals(targetRecipient)) {
                String msg = !sentMessages[i].isEmpty() ? sentMessages[i] :
                             (!storedMessages[i].isEmpty() ? storedMessages[i] : disregardedMessages[i]);
                result += msg + "\n";
            }
        }
        return result.trim().isEmpty() ? "No messages found for this recipient." : result.trim();
    }

    // e. Delete a message using the unique message hash
    public boolean deleteMessageByHash(String hash) {
        for (int i = 0; i < taskCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equalsIgnoreCase(hash)) {
                // Shift elements up to pull down the gap cleanly
                for (int j = i; j < taskCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                    disregardedMessages[j] = disregardedMessages[j + 1];
                    storedMessages[j] = storedMessages[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIds[j] = messageIds[j + 1];
                    recipients[j] = recipients[j + 1];
                    flags[j] = flags[j + 1];
                }
                taskCount--;
                return true;
            }
        }
        return false;
    }

    // f. Comprehensive system layout summary report
    public String displayFullReport() {
        String report = "--- SYSTEM DATA TASK REPORT ---\n";
        for (int i = 0; i < taskCount; i++) {
            String msg = !sentMessages[i].isEmpty() ? sentMessages[i] :
                         (!storedMessages[i].isEmpty() ? storedMessages[i] : disregardedMessages[i]);
            report += "ID: " + messageIds[i] + " | Hash: " + messageHashes[i] + " | Status: " + flags[i] + " | To: " + recipients[i] + " | Text: \"" + msg + "\"\n";
        }
        return report;
    }
}