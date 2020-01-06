package com.example.timeapp.Repositories;

import android.content.ContentValues;
import android.content.Context;
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
import java.util.List;

public class Repository {

    private Context context;

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
        File f = new File(context.getApplicationContext().getFilesDir().getPath()+FILE_NAME);
        FileOutputStream archivo;
        ObjectOutputStream oos;
        Users u;
        try {
            if (!f.exists()){
                archivo = new FileOutputStream(f);
                oos = new ObjectOutputStream(archivo);
                Log.d("asd","Entering for the first time");
                u = new Users(email,username,passwd);
                oos.writeObject(u);
                oos.flush();
                oos.close();
            } else {
                Log.d("asd","File already exists");
                oos = new ObjectOutputStream(new FileOutputStream(f,true)){
                  protected void writeStreamHeader() throws IOException{
                      reset();
                  }
                };
                u = new Users(email,username,passwd);
                oos.writeObject(u);
                oos.flush();
                oos.close();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static boolean checkIfUserIsRegistered(String email,String username,String password,Context context){
        File f = new File(context.getApplicationContext().getFilesDir().getPath()+FILE_NAME);
        FileInputStream fis;
        ObjectInputStream ois;
        Users u;
        boolean output = false;
        try{
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            u = (Users) ois.readObject();

            while (u != null){ // While there is users
                if (username.equals(u.getUsername()) || email.equals(u.getEmailAddress())){
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
