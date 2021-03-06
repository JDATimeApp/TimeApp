package com.example.timeapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timeapp.Database.DBDesign;
import com.example.timeapp.models.Users;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(Users u);

    @Query("SELECT * FROM "+ DBDesign.UserDesign.USER_TABLE+" WHERE username = :username")
    Users checkRegisteredUsername(String username);

    @Query("SELECT * FROM "+ DBDesign.UserDesign.USER_TABLE+" WHERE emailAddress = :email")
    Users checkRegisteredEmail(String email);

    @Query("SELECT * FROM "+ DBDesign.UserDesign.USER_TABLE+" WHERE username =:username AND password =:password")
    Users checkLogin(String username,String password);

    @Query("SELECT * FROM "+ DBDesign.UserDesign.USER_TABLE)
    List<Users> getAllUsers();

    @Query("SELECT Username FROM "+ DBDesign.UserDesign.USER_TABLE+" WHERE UserID = :userId")
    String getUsernameById(String userId);

    @Query("SELECT UserID FROM "+ DBDesign.UserDesign.USER_TABLE+" WHERE username=:username")
    int  getUserId(String username);

    @Query("SELECT * FROM "+DBDesign.UserDesign.USER_TABLE+" WHERE username=:username")
    Users getUserByUsername(String username);
}
