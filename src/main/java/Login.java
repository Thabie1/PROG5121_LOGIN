/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */import java.util.regex.Pattern;

/**
 *
 * @author thabe
 */
public class Login {
  
    private String username;
    private String password;
    private String phone;

    private String savedUsername;
    private String savedPassword;

    public Login(String username, String password, String phone, String doe) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(regex, password);
    }

    public boolean checkCellPhoneNumber() {
        return phone.matches("^\\+27\\d{9}$");
    }

    public String registerUser() {

        if (!checkUserName("Kyl_1")) {
            return "Username incorrect.";
        }

        if (!checkPasswordComplexity("Ch@tt3rApp")) {
            return "Password incorrect.";
        }

        if (!checkCellPhoneNumber()) {
            return "Phone incorrect.";
        }

        savedUsername = username;
        savedPassword = password;

        return "User registered successfully.";
    }

    public boolean loginUser(String u, String p) {
        return u.equals(savedUsername) && p.equals(savedPassword);
    }

    public String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome back!";
        } else {
            return "Login failed.";
        }
    }

}
