package com.example.timeapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.timeapp.Database.DBDesign;

import java.io.Serializable;

@Entity(tableName = DBDesign.UserDesign.USER_TABLE)
public class Users implements Serializable {

    @PrimaryKey(autoGenerate = true) // Autoincremental field
    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN1)
    private String username;

    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN3)
    private String password;

    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN4)
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