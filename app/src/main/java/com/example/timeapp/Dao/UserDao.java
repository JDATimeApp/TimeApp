package com.example.timeapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timeapp.models.Users;

@Dao
public interface UserDao {

    @Insert
    void insertUser(Users u);

}
