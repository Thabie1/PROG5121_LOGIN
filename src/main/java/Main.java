/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */import java.util.Scanner;

/**
 *
 * @author thabe
 */
public class Main {
  
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Register ===");

        System.out.print("Username: ");
        String user = input.nextLine();

        System.out.print("Password: ");
        String pass = input.nextLine();

        System.out.print("Phone (+27...): ");
        String phone = input.nextLine();

        Login obj = new Login(user, pass, phone, "Doe");

        System.out.println(obj.registerUser());

        System.out.println("\n=== Login ===");

        System.out.print("Username: ");
        String u = input.nextLine();

        System.out.print("Password: ");
        String p = input.nextLine();

        boolean result = obj.loginUser(u, p);

        System.out.println(obj.returnLoginStatus(result));
    }

}
