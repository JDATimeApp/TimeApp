package com.example.timeapp.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.timeapp.Database.DBDesign;
import com.example.timeapp.Database.DDBB;
import com.example.timeapp.Database.RoomConnection;
import com.example.timeapp.models.Entry;
import com.example.timeapp.models.Users;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Repository {

    private Context context;

    //Creating a singleton for only one instance
    private static Repository srepository;
    public static SQLiteDatabase db;
    private static RoomConnection r;

    private Repository(Context context) {
        this.context = context;
        DDBB ddbb = new DDBB(context);
        db = ddbb.getWritableDatabase();
        r = RoomConnection.getRoomConnection(context);
    }

    public static Repository get(Context context) {

        if (srepository == null) {
            srepository = new Repository(context);
        }
        return srepository;
    }
    // Here is where we have to put the methods

    public static void closeDatabase(){
        db.close();
    }

    public static void registerNewUser(String email, String username, String passwd, Context context) {
        RoomConnection ro = RoomConnection.getRoomConnection(context);
        Users u = new Users();
        u.setUsername(username);
        u.setEmailAddress(email);
        u.setPassword(passwd);

        ro.userDao().insertUser(u);
        ro.close();
    }

    public static boolean checkIfUserIsRegistered(String email,String username,String password,Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        Users u = ro.userDao().checkRegisteredUsername(username);
        Users u2 = ro.userDao().checkRegisteredEmail(email);
        if (u == null && u2 == null) {
            ro.close();
            return false;
        }
        ro.close();
        return true;
    }

    public static boolean checkLogin(String username,String password,Context c) {
        RoomConnection ro = RoomConnection.getRoomConnection(c);

        Users u =ro.userDao().checkLogin(username,password);

        if (u != null){
            ro.close();
            return true;
        } else {
            ro.close();
            return false;
        }
    }

    public static String getUserId(String username, Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);
        String id = String.valueOf(r.userDao().getUserId(username));
        r.close();
        return id;
    }

    public static List<Users> getUsers (Context context)  {
        RoomConnection r = RoomConnection.getRoomConnection(context);

        List<Users> userList = r.userDao().getAllUsers();
        r.close();
        return userList;
    }

    public static void setEntryTime(String userId, Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);

        Entry e = r.entryDao().checkEntry(userId,getActualDay(getActualDateTime()));
        if (e == null){
            Entry entry = new Entry();
            entry.setWorkerId(userId);
            entry.setEntryDate(getActualDay(getActualDateTime()));
            entry.setEntryTime(getActualHour(getActualDateTime()));
            r.entryDao().insertEntry(entry);
            Log.i("Entry","No entry .. inserting new");
            Toast.makeText(c,"Thank you for checking in!",Toast.LENGTH_SHORT).show();
        } else {
            Log.i("Entry","Found an entry");
            Toast.makeText(c,"Already checked in today!",Toast.LENGTH_SHORT).show();
        }
        r.close();
    }

    public static void setLeaveTime(String userId,Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);

        Entry e = r.entryDao().checkEntryDate(userId,getActualDay(getActualDateTime()));
        if (e == null){
            Log.i("Entry","Something went wrong inserting leave time");
            Toast.makeText(c,"Oops.. Something went wrong!",Toast.LENGTH_SHORT).show();
        } else {
            e.setLeaveTime(getActualHour(getActualDateTime()));
            r.entryDao().updateLeaveTime(e);
            Log.i("Entry","Registered leave time");
            Toast.makeText(c,"See you tomorrow!",Toast.LENGTH_SHORT).show();
        }
        r.close();
    }

    public static List<Entry> getUserEntries(String userId,Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);
        List<Entry> entryList = r.entryDao().getUserEntries(userId);
        r.close();
        return entryList;
    }

    public static String getActualDateTime(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT +1"));
        Date d = new Date();
        String date = df.format(d);
        return date;
    }

    public static String getActualHour(String actualDateTime){
        String [] d = actualDateTime.split(" ");
        return d[1];
    }

    public static String getActualDay(String actualDateTime){
        String [] d = actualDateTime.split(" ");
        return d[0];
    }
}