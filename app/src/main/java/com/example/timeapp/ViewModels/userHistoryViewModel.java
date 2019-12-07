package com.example.timeapp.ViewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.models.Users;

import java.util.ArrayList;

public class userHistoryViewModel extends ViewModel {

    public ArrayList<Users> getUsersList(Context c){
        ArrayList<Users> userList = new ArrayList<>();
        userList = Repository.getUsers(c);
        return userList;
    }
}
