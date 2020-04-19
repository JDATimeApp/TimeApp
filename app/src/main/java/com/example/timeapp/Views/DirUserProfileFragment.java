package com.example.timeapp.Views;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.DirUserProfileViewModel;
import com.example.timeapp.models.Users;
import com.squareup.picasso.Picasso;

public class DirUserProfileFragment extends Fragment {

    private DirUserProfileViewModel dirUserProfileViewModel;
    private TextView usernameTxtView,emailTxtView;
    private ImageView profileImage;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dir_userprofile,container,false);
        dirUserProfileViewModel = ViewModelProviders.of(this).get(DirUserProfileViewModel.class);

        profileImage = root.findViewById(R.id.userProfileDirImg);

        usernameTxtView = root.findViewById(R.id.usernameDirTxtView);
        emailTxtView = root.findViewById(R.id.emailDirTxtView);

        if (getArguments() != null) {
            dirUserProfileViewModel.getUserInformation(getArguments().getString("username"),
                    getArguments().getString("department"));

            dirUserProfileViewModel.downloadUserProfileImage(getArguments().getString("username"));

            dirUserProfileViewModel.getUserProfileImage().observe(getViewLifecycleOwner(), new Observer<Uri>() {
                @Override
                public void onChanged(Uri uri) {
                    Picasso.with(getContext()).load(uri).into(profileImage);
                }
            });

        }

        dirUserProfileViewModel.getUserInformation().observe(getViewLifecycleOwner(), new Observer<Users>() {
            @Override
            public void onChanged(Users users) {
                if (users != null) {
                    usernameTxtView.setText(users.getUsername());
                    emailTxtView.setText(users.getEmailAddress());
                }
            }
        });

        return root;
    }
}
