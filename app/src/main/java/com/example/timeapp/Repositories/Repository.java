package com.example.timeapp.Repositories;

import android.content.Context;
import com.example.timeapp.models.Users;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private Context context;

    //Creating a singleton for only one instance
    private static Repository srepository;

    private static List<Users> userList;

    private static final String FILE_NAME = "users.dat";

    private Repository(Context context) {
        this.context = context;
    }

    public static Repository get(Context context) {

        if (srepository == null) {
            srepository = new Repository(context);
        }
        return srepository;
    }

    // Here is where we have to put the methods

    public static boolean registerNewUser(String username, String passwd, String email, Context context)  throws FileNotFoundException{
        userList = new ArrayList<>();
        FileOutputStream archivo;
        try {
            archivo = context.getApplicationContext().openFileOutput(FILE_NAME, Context.MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(archivo);
            Users u = new Users(email,username,passwd);
            userList.add(u);
            oos.writeObject(u);
            oos.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
