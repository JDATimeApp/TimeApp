package com.example.timeapp.ViewModels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DirUserProfileViewModel extends ViewModel {


    private MutableLiveData<Users> userInformation;

    public DirUserProfileViewModel(){
        userInformation = new MutableLiveData<>();
    }

    public MutableLiveData<Users> getUserInformation() {return userInformation;}

    public void getUserInformation(String user,String department){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("Departments").child(department).child(user);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userInformation.postValue(dataSnapshot.getValue(Users.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("UserInformation","Error while getting user information from directory");
            }
        });
    }

}
