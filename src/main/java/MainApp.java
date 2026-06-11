/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import com.mycompany.prog5121_login.TaskManager;
import java.util.Scanner;
/**
 *
 * @author thabe
 */
public class MainApp {
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       TaskManager manager = new TaskManager();
       manager.populateTestData();
       
        System.out.println("Welcome to QuickChat!");
        System.out.print("Enter how many messages you wish to enter: ");
        int maxMessages = scanner.nextInt();
        scanner.nextLine(); // Clear memory buffer

        Message[] messageStorage = new Message[maxMessages];
        int accumulatedSentMessages = 0;
        int indexTracker = 0;

        boolean running = true;
        while (running) {
            System.out.println("\n--- QuickChat Main Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("(4) Stored Messages & Reports"); 
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
           
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    if (indexTracker >= maxMessages) {
                        System.out.println("Error: Limit reached. You cannot enter more messages than specified.");
                        break;
                    }

                    // 1. Gather Target Details
                    System.out.print("Enter Recipient Cell Number (e.g. +27711234567): ");
                    String cell = scanner.nextLine();
                   
                    System.out.print("Enter Message (Max 250 Chars): ");
                    String text = scanner.nextLine();

                    // Temporary testing instance to check rules
                    Message tempMsg = new Message(indexTracker, cell, text);

                    // 2. Validate Constraints
                    if (!tempMsg.checkRecipientCell().equals("Call phone number successfully captured.")) {
                        System.out.println(tempMsg.checkRecipientCell());
                        break;
                    }
                    if (!tempMsg.checkMessageLength().equals("Message ready to send.")) {
                        System.out.println(tempMsg.checkMessageLength());
                        break;
                    }

                    // 3. User Handling Sub-Menu
                    System.out.println("\nMessage validated. Choose an action:");
                    System.out.println("1 - Send Message");
                    System.out.println("2 - Disregard Message");
                    System.out.println("3 - Store Message to send later");
                    System.out.print("Your selection: ");
                    int actionChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (actionChoice == 1) {
                        System.out.println("Message successfully sent.");
                        System.out.println("Message ID generated: <" + tempMsg.getMessageId() + ">");
                       
                        // Save to tracking storage array
                        messageStorage[indexTracker] = tempMsg;
                        indexTracker++;
                        accumulatedSentMessages++;
                    }
                    else if (actionChoice == 2) {
                        System.out.println("Press 0 to delete the message.");
                        int confirmDelete = scanner.nextInt();
                        if (confirmDelete == 0) {
                            System.out.println("Message discarded.");
                        }
                    }
                    else if (actionChoice == 3) {
                        
                        System.out.println("Message successfully stored.");
                        // Save configuration to array slot but handle sending metrics accordingly
                        messageStorage[indexTracker] = tempMsg;
                        indexTracker++;
                        // Call our research method to write the data permanently onto the disk
                        tempMsg.storeMessage();
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                    case 4:
            System.out.println("\n--- SUBMENU: STORED MESSAGES ---");
            System.out.println("a. Display all stored senders/recipients");
            System.out.println("b. Display longest stored message");
            System.out.println("c. Search by Message ID");
            System.out.println("d. Search by Recipient Number");
            System.out.println("e. Delete a message using Hash");
            System.out.println("f. Display full details report");
            System.out.print("Choose sub-option (a-f): ");
           
            char subChoice = scanner.next().toLowerCase().charAt(0);
            scanner.nextLine(); // Clear trailing text buffer
           
            switch(subChoice) {
                case 'a':
                    System.out.println(manager.displayStoredMessages());
                    break;
                case 'b':
                    System.out.println("Longest Message: " + manager.getLongestStoredMessage());
                    break;
                case 'c':
                    System.out.print("Enter Message ID to find: ");
                    String mid = scanner.nextLine();
                    System.out.println(manager.searchByMessageId(mid));
                    break;
                case 'd':
                    System.out.print("Enter Recipient Phone Number: ");
                    String rPhone = scanner.nextLine();
                    System.out.println(manager.searchByRecipient(rPhone));
                    break;
                case 'e':
                    System.out.print("Enter Message Hash code to delete: ");
                    String hKey = scanner.nextLine();
                    if (manager.deleteMessageByHash(hKey)) {
                        System.out.println("Message successfully deleted.");
                    } else {
                        System.out.println("Message hash code not found.");
                    }
                    break;
                case 'f':
                    System.out.println(manager.displayFullReport());
                    break;
                default:
                    System.out.println("Invalid structural selection choice.");
            }
            break;

                    
                case 3:
                    running = false;
                    System.out.println("\nExiting application...");
                    System.out.println("Total count of successful messages handled: " + accumulatedSentMessages);
                    break;

                    
                default:
                    System.out.println("Invalid numeric selection. Try again.");
            }
        }
        scanner.close();
    }
    
}     
