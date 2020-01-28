package com.example.timeapp.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.signViewModel;

public class signFragment extends Fragment {

    private com.example.timeapp.ViewModels.signViewModel signViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signViewModel =
                ViewModelProviders.of(this).get(signViewModel.class);
        View root = inflater.inflate(R.layout.sign_fragment, container, false);
        //Getting the user ID from sharedPreferences
        SharedPreferences pref = getContext().getSharedPreferences("userInfo",0);
        final String userId  = pref.getString("userId","");

        Button entryBtn,leaveBtn;
        entryBtn = root.findViewById(R.id.entryBtn);
        leaveBtn = root.findViewById(R.id.leaveBtn);
        entryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signViewModel.setEntryTime(userId,getContext());
            }
        });

        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signViewModel.setLeaveTime(userId,getContext());
            }
        });
        return root;
    }
}
