package com.example.timeapp.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.timeapp.Dao.UserDao;
import com.example.timeapp.models.Users;

@Database(entities = {Users.class}, version = 1)
public abstract class RoomConnection extends RoomDatabase {

    public abstract UserDao userDao();

}
