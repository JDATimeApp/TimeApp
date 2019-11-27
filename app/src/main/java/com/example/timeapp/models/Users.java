package com.example.timeapp.models;

import java.io.Serializable;

public class Users implements Serializable {

    private String username;
    private String password;
    private String emailAddress;

    public Users(String emailAddress,String username, String password) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}