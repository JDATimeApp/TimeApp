package com.example.timeapp.Repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.timeapp.Database.DDBB;
import com.example.timeapp.Database.RoomConnection;
import com.example.timeapp.models.Entry;
import com.example.timeapp.models.Incidence;
import com.example.timeapp.models.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Repository {

    //private Context context;

    //Creating a singleton for only one instance
    private static Repository srepository;
    public static SQLiteDatabase db;
    private static RoomConnection r;

    // *** PostgreSQL Constants ***
    private static String driverLocation = "org.postgresql.Driver";
    private static String postgresConnection = "jdbc:postgresql://192.168.0.22:5432/";
    private static String postgresUsername = "hedy";
    private static String postgresPassword = "lamarr";
    // *** End of PostgreSQL String ***


    private Repository(Context context) {
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
        r.destroyRoomConnection();
        ro.close();
    }

    public static boolean checkIfUserIsRegistered(String email,String username,Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        Users u = ro.userDao().checkRegisteredUsername(username);
        Users u2 = ro.userDao().checkRegisteredEmail(email);
        if (u == null && u2 == null) {
            r.destroyRoomConnection();
            ro.close();
            return false;
        }
        r.destroyRoomConnection();
        ro.close();
        return true;
    }

    public static boolean checkLogin(String username,String password,Context c) {
        RoomConnection ro = RoomConnection.getRoomConnection(c);

        Users u =ro.userDao().checkLogin(username,password);

        if (u != null){
            r.destroyRoomConnection();
            ro.close();
            return true;
        } else {
            r.destroyRoomConnection();
            ro.close();
            return false;
        }
    }

    public static String getUserId(String username, Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);
        String id = String.valueOf(r.userDao().getUserId(username));
        r.destroyRoomConnection();
        r.close();
        return id;
    }

    public static String getUsernameById(String userId,Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);
        String username = r.userDao().getUsernameById(userId);
        r.destroyRoomConnection();
        r.close();
        return username;
    }

    public static List<Users> getUsers (Context context)  {
        RoomConnection r = RoomConnection.getRoomConnection(context);

        List<Users> userList = r.userDao().getAllUsers();
        r.destroyRoomConnection();
        r.close();
        return userList;
    }

    public static boolean setEntryTime(String userId, Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);
        boolean output = false;
        Entry e = r.entryDao().checkEntry(userId,getActualDay(getActualDateTime()));
        if (e == null){
            Entry entry = new Entry();
            entry.setWorkerId(userId);
            entry.setEntryDate(getActualDay(getActualDateTime()));
            entry.setEntryTime(getActualHour(getActualDateTime()));
            r.entryDao().insertEntry(entry);
            output = true;
            Log.i("Entry","No entry .. inserting new");

        } else {
            Log.i("Entry","Found an entry");
        }
        r.destroyRoomConnection();
        r.close();

        return output;
    }

    public static boolean setLeaveTime(String userId,Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);
        boolean output = false;
        Entry e = r.entryDao().checkEntryDate(userId,getActualDay(getActualDateTime()));
        if (e == null){
            Log.i("Entry","Something went wrong inserting leave time");
        } else {
            e.setLeaveTime(getActualHour(getActualDateTime()));
            r.entryDao().updateLeaveTime(e);
            Log.i("Entry","Registered leave time");
            output = true;
        }
        r.destroyRoomConnection();
        r.close();

        return output;
    }

    public static List<Entry> getUserEntries(String userId,Context c){
        RoomConnection r = RoomConnection.getRoomConnection(c);
        List<Entry> entryList = r.entryDao().getUserEntries(userId);
        r.destroyRoomConnection();
        r.close();
        return entryList;
    }

    public static void reportIncidence(Incidence i){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child("Incidences");
        ref.push().setValue(i);
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
    private static Connection connection = null;

    public static Connection openPostgresConnection(){
        try {
            Class.forName(driverLocation);

            connection = DriverManager.getConnection(postgresConnection,postgresUsername,
                    postgresPassword);

            if (connection != null){
                Log.d("PostgreSQL","Connected to the postgres server successfully");
            } else {
                Log.d("PostgreSQL","Failed to connect");
            }

        } catch (SQLException e) {
            Log.d("PostgreSQL","Error connecting to PostgreSQL server");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}