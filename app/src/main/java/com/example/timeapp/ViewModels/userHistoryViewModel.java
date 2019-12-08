package com.example.timeapp.ViewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.models.Users;

import java.util.ArrayList;

public class userHistoryViewModel extends ViewModel {

    public ArrayList<Users> getUsersList(Context c){
        ArrayList<Users> a;
        a = Repository.getUsers(c);
        Log.d("lista","Lista"+a);
        return a;
    }
}
