package com.example.timeapp.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timeapp.Dao.UserDao;
import com.example.timeapp.models.Users;

@Database(entities = {Users.class}, version = 1)
public abstract class RoomConnection extends RoomDatabase {

    private static RoomConnection INSTANCE;

    public abstract UserDao userDao();

    public static RoomConnection getRoomConnection(Context c){
        if (INSTANCE == null){
            Room.databaseBuilder(c,RoomConnection.class,"timeapp.db").build();
        }
        return INSTANCE;
    }

    public static void destroyRoomConnection() {
        INSTANCE = null;
    }

}
