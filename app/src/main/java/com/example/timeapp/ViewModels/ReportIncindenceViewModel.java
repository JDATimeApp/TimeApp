package com.example.timeapp.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.models.Incidence;

public class ReportIncindenceViewModel extends ViewModel {

    public static void reportIncidence(Incidence i){
        Repository.reportIncidence(i);
    }
}