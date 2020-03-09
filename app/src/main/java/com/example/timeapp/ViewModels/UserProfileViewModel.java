package com.example.timeapp.ViewModels;


import android.content.Intent;
import android.provider.MediaStore;

import androidx.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {
    public static Intent chargeImageGalery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        return intent;
    }


}
