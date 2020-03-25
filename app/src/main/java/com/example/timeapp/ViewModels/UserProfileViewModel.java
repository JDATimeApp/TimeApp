package com.example.timeapp.ViewModels;


import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserProfileViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> departmentList;
    private ArrayList<String> departments;

    public UserProfileViewModel(){
        departmentList = new MutableLiveData<ArrayList<String>>();
        departments = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<String>> getDepartmentList() {
        return departmentList;
    }

    public static Intent chargeImageGalery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        return intent;
    }

    public void getDepartments(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("Departments");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    if (!d.getKey().equals("No department")){
                        departments.add(d.getKey());
                        departmentList.postValue(departments);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DepartmentList","Error while getting department data!");
            }
        });

    }

    public ArrayAdapter populateSpinner(ArrayList<String> dpt, Context c){
        // The layout referenced is a ViewHolder for each item of the spinner
        return new ArrayAdapter<String>(c,R.layout.support_simple_spinner_dropdown_item, dpt);
    }

}
