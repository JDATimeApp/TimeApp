package com.example.timeapp.models;

public class Incidence {

    String userId;
    String subject;
    String message;
    String date;
    String username;

    public Incidence() {}

    public Incidence(String userId, String username, String subject, String message, String date) {
        this.userId = userId;
        this.message = message;
        this.date = date;
        this.subject = subject;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
