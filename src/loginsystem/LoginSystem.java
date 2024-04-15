/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;

import java.io.File;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author michael.roy-diclemen
 */
public class LoginSystem {
   static ArrayList<User> users = new ArrayList<>();
    public static void main(String[] args) {
    LoginSystem loginSystem = new LoginSystem();
    UserRegistration registration = new UserRegistration(loginSystem);
        
    // User a = new User("bright", "brighton", "truong", "abc", "brightontruong@gmail.com");
   //  users.add(a);   
     
   //  User b = new User("justin", "justin", "justin", "jjj", "justmin@");
    // users.add(b);
     registration.registerUser();
     loadUsers();
    }
    
    public static void addUser(User user) {
        users.add(user);
    }
    
    public boolean usernameExists(String username) {
        try {
        File file = new File("src/LoginSystem/UserList.txt");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");

           
            if (fields.length > 0 && fields[0].equalsIgnoreCase(username)) {
                scanner.close();
                return true;
            }
        }
        scanner.close();
    } catch (Exception a) {
    
    }
    return false; 
}
    
    
    public static void userSave(){
        PrintWriter pw;
        try{ 
            pw = new PrintWriter(new File("src/LoginSystem/UserList.txt"));
            for(int i = 0; i< users.size(); i++){
                User user = users.get(i);
              //  String encrytpedPassword = encryption(user.getPassword());
                String userInfo = user.getUsername() + ", " + user.getName() + ", " + user.getLastname() + ", " + user.getPassword() + ", " + user.getEmail();
                pw.println(userInfo);

            }
            pw.close();
        }
        catch(Exception a){
            
        }
    }
     
    /**
     *
     */
    public static void loadUsers(){
         try{
             Scanner sc = new Scanner(new File("src/LoginSystem/UserList.txt"));
             sc.useDelimiter(",");
             while(sc.hasNext()){
                 String line = sc.nextLine();
                 String[] parts = line.split(",");
                 if (parts.length == 6){
                     String username = parts[0];
                     String name = parts[1];
                     String lastname = parts[2];
                     String password = parts[3];
                     String salt = parts[4];
                     String email = parts[5];
                    User user = new User(username, name, lastname, password, salt, email); 
                // users.add(user);
                 }
                }
             sc.close();
             
             for(int i = 0; i < users.size(); i++){
                 User user = users.get(i);
                 System.out.println("Username: " + user.getUsername());
                 System.out.println("Name: " + user.getName());
                 System.out.println("Last Name: " + user.getLastname());
                 System.out.println("Password: " + user.getPassword());
                 System.out.println("Salt: " + user.getSalt());
                 System.out.println("Email: " + user.getEmail());
                 } 
            }
         
         catch(Exception a){
             
        }     
    }
    
    public static boolean isBadPassword(String password) {
        try {
            File badPasswordsFile = new File("src/LoginSystem/BadPasswords.txt");
            Scanner scanner = new Scanner(badPasswordsFile);

            while (scanner.hasNextLine()) {
                String badPassword = scanner.nextLine().trim();
                if (badPassword.equals(password)) {
                    scanner.close();
                    return true; 
                }
            }
        }
         catch(Exception a){
             
        } 
        return false;
    }

    public static String encryption(String password, String salt){
        try{
        password += salt;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        String enpass="";
            for (int i = 0; i < byteData.length; ++i) {
                enpass += (Integer.toHexString((byteData[i] & 0xFF) |
                0x100).substring(1,3));
            }
            return enpass;
            
      } catch (Exception e) {
          
      }
      return password;
    }  
    
    public static String generateSalt() {
        String salt = "";
        for (int i = 0; i < 3; i++ ){
            int rand = (int) (Math.random() * 10); 
            salt += Integer.toString(rand);
        }
        return salt;
    }
  
    
    
    
    
    
}