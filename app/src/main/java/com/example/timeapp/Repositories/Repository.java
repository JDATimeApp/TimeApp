package com.example.timeapp.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.timeapp.Database.DBDesign;
import com.example.timeapp.Database.DDBB;
import com.example.timeapp.models.Users;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Repository {

    private static Context context;

    //Creating a singleton for only one instance
    private static Repository srepository;
    public static SQLiteDatabase db;

    private Repository(Context context) {
        this.context = context;
        DDBB ddbb = new DDBB(context);
        db = ddbb.getWritableDatabase();
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
        DDBB db = new DDBB(context);
        SQLiteDatabase sql = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBDesign.UserDesign.USER_COLUMN2,username);
        values.put(DBDesign.UserDesign.USER_COLUMN3,passwd);
        values.put(DBDesign.UserDesign.USER_COLUMN4,email);
        sql.insert(DBDesign.UserDesign.USER_TABLE, null, values);
        sql.close();
    }



    public static boolean checkIfUserIsRegistered(String email,String username,String password,Context c){
        DDBB db = new DDBB(c);
        SQLiteDatabase sql = db.getReadableDatabase();

        String [] columns = {"*"}; // Row to find
        String select = DBDesign.UserDesign.USER_COLUMN2+" = ? and "+DBDesign.UserDesign.USER_COLUMN4+" = ?";
        String [] selectArgs  = {username,email};

        Cursor cu = sql.query(DBDesign.UserDesign.USER_TABLE,
                columns,
                select,
                selectArgs,
                null,
                null,
                null);

        int result = cu.getCount();
        cu.close();
        if (result > 0){
            return true;
        }
        sql.close();
        return false;

    }

    public static boolean checkLogin(String username,String password,Context c) {
        DDBB db = new DDBB(c);
        SQLiteDatabase sql = db.getReadableDatabase();

        String[] columns = {"*"}; // Row to find
        String select = DBDesign.UserDesign.USER_COLUMN2 + " = ? and " + DBDesign.UserDesign.USER_COLUMN4 + " = ?";
        String[] selectArgs = {username, password};
        Cursor cu = sql.query(DBDesign.UserDesign.USER_TABLE,
                columns,
                select,
                selectArgs,
                null,
                null,
                null);
        int result = cu.getCount();
        if (result > 0) {
            return true;
        }
        sql.close();
        return false;
    }

    public static String getUserId(String username){
        DDBB ddbb = new DDBB(context);
        SQLiteDatabase sql = ddbb.getReadableDatabase();

        String [] columns = {DBDesign.UserDesign.USER_COLUMN1};
        String select = DBDesign.UserDesign.USER_COLUMN2 + "= ?";
        String [] selectArgs = {username};

        Cursor cu = sql.query(DBDesign.UserDesign.USER_TABLE,
                columns,
                select,
                selectArgs,
                null,
                null,
                null);
        String id = String.valueOf(cu.getInt(0));
        cu.close();
        sql.close();

        return id;
    }

    public static ArrayList<Users> getUsers (Context context)  {
        ArrayList<Users> userList = new ArrayList<>();
        String [] columns = {"*"};

        DDBB ddbb = new DDBB(context);
        SQLiteDatabase sql = ddbb.getReadableDatabase();

       Cursor c = sql.query(DBDesign.UserDesign.USER_TABLE,
               columns,
               null,
               null,
               null,
               null,
               null);

       if (c.moveToFirst()){
           do{
               // Getting the info from every row
               String username = c.getString(1);
               String password = c.getString(2);
               String email = c.getString(3);

               userList.add(new Users(email,username,password));
           } while (c.moveToNext());
       }
        c.close();
        return userList;
    }

    public static void setEntryTime(String userId){
        DDBB ddbb = new DDBB(context);
        SQLiteDatabase sql = ddbb.getReadableDatabase();

        String [] columns = {"*"};
        String select = DBDesign.UserDesign.USER_COLUMN1 + "=? and"+DBDesign.EntryDesign.ENTRY_COLUMN1+ "=?";
        String [] selectArgs = {userId,getActualDay(getActualDateTime())};

        Cursor cu = sql.query(DBDesign.UserDesign.USER_TABLE,
                columns,
                select,
                selectArgs,
                null,
                null,
                null);
        int total = cu.getCount();
        if (total > 0){
            Log.i("Entry","Found an entry"+cu.getString(0)+" "+cu.getString(1)+
                    ""+cu.getString(2));
        } else {
            Log.i("Entry","No entry .. inserting new");
        }
        cu.close();
        sql.close();
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