package com.example.timeapp.ViewModels;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

public class DirUserProfileViewModel extends ViewModel {


    private MutableLiveData<Users> userInformation;
    private MutableLiveData<Uri> userProfileImage;


    public DirUserProfileViewModel(){
        userInformation = new MutableLiveData<>();
        userProfileImage = new MutableLiveData<>();
    }

    public MutableLiveData<Users> getUserInformation() {return userInformation;}

    public MutableLiveData<Uri> getUserProfileImage() {return userProfileImage;}

    public void getUserInformation(String user, String department){

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

    public void downloadUserProfileImage(String username){

        StorageReference ref = FirebaseStorage.getInstance().getReference().child(username);

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                userProfileImage.postValue(uri);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Profile","Error downloading profile image");
                    }
                });
    }

}
