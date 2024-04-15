/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author brigh
 */
public class User {
   String username;
   private String name;
   private String lastname;
   private String password;
   private String email;
   private String salt;
   boolean getname;

    public User(String username, String name, String lastname, String password, String salt, String email) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.salt = salt;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt(){
        return salt;
    }
    
    public void setSalt(String salt){
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}




