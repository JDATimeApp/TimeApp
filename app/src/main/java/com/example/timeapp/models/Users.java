package com.example.timeapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.timeapp.Database.DBDesign;
import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = DBDesign.UserDesign.USER_TABLE)
public class Users implements Serializable {

    @PrimaryKey(autoGenerate = true) // Autoincremental field
    @NonNull
    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN1)
    private int idU;

    @NonNull
    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN2)
    private String username;

    @NonNull
    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN3)
    private String password;

    @NonNull
    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN4)
    private String emailAddress;

    public Users(){}

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

    public int getIdU() {return idU;}

    public void setIdU(int idU) {this.idU = idU;}

}