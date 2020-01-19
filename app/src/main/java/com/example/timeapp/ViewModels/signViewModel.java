package com.example.timeapp.ViewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;

public class signViewModel extends ViewModel {
    public static void setEntryTime(String userId, Context c){
        Repository.setEntryTime(userId,c);
    }
}
