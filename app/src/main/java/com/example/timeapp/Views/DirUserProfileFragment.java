package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.DirUserProfileViewModel;

public class DirUserProfileFragment extends Fragment {

    private DirUserProfileViewModel dirUserProfileViewModel;
    private TextView usernameTxtView,emailTxtView,passwordTxtView;
    private ImageView profileImage;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dir_userprofile,container,false);
        dirUserProfileViewModel = ViewModelProviders.of(this).get(DirUserProfileViewModel.class);

        profileImage = root.findViewById(R.id.userProfileDirImg);

        usernameTxtView = root.findViewById(R.id.usernameDirTxtView);
        emailTxtView = root.findViewById(R.id.emailDirTxtView);
        passwordTxtView = root.findViewById(R.id.passwordDirTxtView);

        return root;
    }
}
