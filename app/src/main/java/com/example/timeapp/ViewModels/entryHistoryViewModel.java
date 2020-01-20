package com.example.timeapp.ViewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.models.Entry;

import java.util.ArrayList;

public class entryHistoryViewModel extends ViewModel {
    public static ArrayList<Entry> getUserEntries(String userId, Context c){
        return Repository.getUserEntries(userId,c);
    }
}
