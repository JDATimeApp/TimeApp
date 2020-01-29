package com.example.timeapp.ViewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class entryHistoryViewModel extends ViewModel {
    public static List<Entry> getUserEntries(String userId, Context c){
        return Repository.getUserEntries(userId,c);
    }
}
