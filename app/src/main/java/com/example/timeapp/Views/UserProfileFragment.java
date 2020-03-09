package com.example.timeapp.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.UserProfileViewModel;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel userProfileViewModel;

    private ImageView userProfileImageView;
    private EditText username,email,password;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);

        View root = inflater.inflate(R.layout.user_profile, container, false);

        userProfileImageView = root.findViewById(R.id.userProfileImg);
        username = root.findViewById(R.id.usernameTxtView);
        email = root.findViewById(R.id.emailTxtView);
        password = root.findViewById(R.id.passwordTxtView);

        userProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(UserProfileViewModel.chargeImageGalery(),10);


            }
        });



        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;

        if(requestCode == 10 && resultCode == RESULT_OK){
            Uri uri;
            uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                userProfileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }





    }
}
