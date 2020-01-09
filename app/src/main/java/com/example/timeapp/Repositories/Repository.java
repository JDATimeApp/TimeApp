package com.example.timeapp.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Database.DBDesign;
import com.example.timeapp.Database.DDBB;
import com.example.timeapp.models.Users;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repository {

    private static Context context;

    //Creating a singleton for only one instance
    private static Repository srepository;
    public static SQLiteDatabase db;
    private static List<Users> userList;

    private static final String FILE_NAME = "/users.dat";

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
        return false;

    }

    public static boolean checkLogin(String username,String password,Context c){
        File f = new File(c.getApplicationContext().getFilesDir().getPath()+FILE_NAME);
        FileInputStream fis;
        ObjectInputStream ois;
        Users u;
        boolean output = false;
        try{
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            u = (Users) ois.readObject();

            while (u != null){ // While there is users
                if (username.equals(u.getUsername()) && password.equals(u.getEmailAddress())){
                    Log.d("asd","Devuelve true");
                    ois.close();
                    output = true;
                    break;
                }
                u = (Users) ois.readObject();
            }
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (EOFException e){
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return output;
    }

    public static ArrayList<Users> getUsers (Context context)  {
        ArrayList<Users> userList = new ArrayList<>();
        File f = new File(context.getApplicationContext().getFilesDir().getPath()+FILE_NAME);
        FileInputStream fis;
        ObjectInputStream ois;
        Users u;

        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            while (true) {
                u = (Users) ois.readObject();
                userList.add(u);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("lista","lista : "+userList);
        return userList;
    }
}
