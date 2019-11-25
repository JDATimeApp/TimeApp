package com.example.timeapp.Repositories;

import android.content.Context;

import com.example.timeapp.models.Users;

import java.util.ArrayList;
import java.util.List;

public class Repository {


    //Creating a singleton for only one instance
    private static Repository srepository;

    private List<Users> userList;

    private Repository(){
        userList = new ArrayList<>();
    }

    public static Repository get(Context context){

        if( srepository == null){
            srepository = new Repository();
        }
        return srepository;
    }

    // Here is where we have to put the methods

}
