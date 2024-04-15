
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 *
 * @author brigh
 */
public class UserRegistration {
    private LoginSystem loginSystem;

    public UserRegistration(LoginSystem loginSystem) {
        this.loginSystem = loginSystem;
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();

        String username;
        boolean validUsername = false;
        do {
            System.out.print("Enter username: ");
            username = scanner.nextLine();

            if (loginSystem.usernameExists(username)) {
                System.out.println("This username is already taken, please enter in a new one");
            } else {
                validUsername = true;
            }
        } while (!validUsername);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastname = scanner.nextLine();
        
        String password;
        boolean validPassword = false;
        int strengthCounter = 0;
        do {
            System.out.print("Enter password {must contain atleast 8 characters and at least one upper or lowercase letter, number, special character} : ");
            password = scanner.nextLine();
            strengthCounter = 0;
            
            if (loginSystem.isBadPassword(password)) {
                System.out.println("This password is a bad password, please type a different one.");
            continue;
            }
            
            if (password.length() >= 8) {
                strengthCounter++;
            }
            if (password.matches(".*[ABCDEFGHIJKLMNOPQRSTUVWXYZ].*")) {
                strengthCounter++; 
            }   
            if (password.matches(".*[abcdefghijklmnopqrstuvwxyz].*")) {
                strengthCounter++; 
            }   
            if (password.matches(".*[0-9].*")) {
                strengthCounter++; 
            }
            if (password.matches(".*[!@#$%^&*()-_=+\\|\\[{\\]};:'\",<.>/?`~].*")) {
                strengthCounter++; 
            }

            if (strengthCounter < 5) {
                System.out.println("Password must meet at least 5 strength criteria, try again.");
            }
        } while (strengthCounter < 5);
        
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        
        String salt = loginSystem.generateSalt();
        String enpass = loginSystem.encryption(password, salt);
        
        
        User newUser = new User(username, name, lastname, enpass, salt, email);
        loginSystem.addUser(newUser);
        
        try (PrintWriter pw = new PrintWriter(new FileWriter("src/LoginSystem/UserList.txt", true))) {
        pw.println(newUser.getUsername() + ", " + newUser.getName() + ", " + newUser.getLastname() + ", " + enpass + ", " + salt + ", " + newUser.getEmail());
        } catch (Exception e) {
        
        }
        System.out.println("Registration complete");
    }
}
