package com.example.timeapp.ViewModels;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;

public class ControlPanelViewModel extends ViewModel {

    public static void addDepartment(String department, FragmentManager fm){
        Repository.addDepartment(department,fm);
    }

}
