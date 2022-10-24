package com.example.meisner_band_app;

public class UserMap {
    private String username;
    private String password;

    public UserMap(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void display() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
