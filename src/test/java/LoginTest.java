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
public class LoginTest {
    
    
    Login login = new Login("kyl_1","Chr1st@as","John","Doe"); 

    @Test
    public void testUsernameCorrect() {
        // Test case: "Kyl_1" should return true
        boolean result = 

login.checkUserName("Kyl_1"); // Act
        assertTrue(result); // Assert
    }

    @Test
    public void testUsernameIncorrect() {
        // Test case: "kyl_1!!!!!!!" should return false
        boolean result = login.checkUserName("kyl_1_!!!!!!!!"); // Act
        assertFalse(result); // Assert
    }

    @Test
    public void testPasswordComplexitySuccess() {
        // Meets all criteria: 8+ chars, Cap, Number, Special
        assertTrue(login.checkPasswordComplexity("Ch@tt3rApp"));
    }
}

