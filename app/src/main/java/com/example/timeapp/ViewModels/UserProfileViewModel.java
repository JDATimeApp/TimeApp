package com.example.timeapp.ViewModels;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.R;
import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.models.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class UserProfileViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> departmentList;
    private MutableLiveData<Uri> profileImageUri;
    private ArrayList<String> departments;

    public UserProfileViewModel(){
        departmentList = new MutableLiveData<ArrayList<String>>();
        profileImageUri = new MutableLiveData<>();
        departments = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<String>> getDepartmentList() {
        return departmentList;
    }

    public MutableLiveData<Uri> getProfileImageUri() {return profileImageUri;}

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
                    if (!d.getKey().equals("NoDepartment")){
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

    public void moveOrAddUserDepartment(final String department,Context c) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference oldRef = db.getReference().child("Departments");

            SharedPreferences pref = c.getSharedPreferences("userInfo",0);
            final String key = pref.getString("username","");

            oldRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean found = false;
                    // This label is made to break the double for loop
                    outer:
                    for (DataSnapshot depts : dataSnapshot.getChildren()){

                        Log.i("Department",depts.getKey());

                        for(DataSnapshot users : depts.getChildren()){

                            Log.d("User",users.getKey());

                            if (users.getKey().equals(key)){
                                Log.d("Firebase",users.getKey());

                                DatabaseReference remove = users.getRef();
                                Users u = users.getValue(Users.class);
                                remove.setValue(null);

                                DatabaseReference newRef = oldRef.child(department).child(u.getUsername());
                                newRef.setValue(u);

                                found = true;

                                break outer;
                            }
/*
                            if (!found){
                                //DatabaseReference newUser = oldRef.child(department).child();
                            } */
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("Firebase","Some problem while looking for the user");
                }
            });
    }

    public static void uploadUserProfileImage(Uri uri, String username){
        Repository.uploadUserProfileImage(uri,username);
    }

    public void downloadUserProfileImage(String username){

        StorageReference ref = FirebaseStorage.getInstance().getReference().child(username);

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                profileImageUri.postValue(uri);
            }
        })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Profile","Error downloading profile image");
                }
            });
    }

    public String getSharedUsername(Context context){
        SharedPreferences pref = context.getSharedPreferences("userInfo",0);
        String username = pref.getString("username","");
        return username;
    }

}
