package com.example.timeapp.models;

public class Incidence {

    String userId;
    String message;
    String date;

    public Incidence(String userId, String message, String date) {
        this.userId = userId;
        this.message = message;
        this.date = date;
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
}
