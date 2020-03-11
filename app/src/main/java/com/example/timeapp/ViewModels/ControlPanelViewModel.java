package com.example.timeapp.ViewModels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ControlPanelViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> departmentList;
    private ArrayList<String> departments;

    public ControlPanelViewModel(){
        departmentList = new MutableLiveData<>();
        departments = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<String>> getDepartmentList() {
        return departmentList;
    }



    public static void addDepartment(String department, FragmentManager fm){
        Repository.addDepartment(department,fm);
    }

    public void getDepartments(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("Departments");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    departments.add(d.getKey());
                    departmentList.postValue(departments);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DepartmentList","Error while getting department data!");
            }
        });

    }

}
