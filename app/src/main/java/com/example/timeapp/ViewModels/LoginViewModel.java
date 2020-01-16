package com.example.timeapp.ViewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;

public class LoginViewModel extends ViewModel {
    public static boolean checkLogin(String username, String password, Context c){
        Boolean result = Repository.checkLogin(username,password,c);

        if (result == true){
            return true;
        } else {
            return false;
        }
    }

    public static String getUserId(String username){
        return Repository.getUserId(username);
    }
}