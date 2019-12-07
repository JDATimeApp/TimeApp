package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.ViewModels.userHistoryViewModel;
import com.example.timeapp.R;
import com.example.timeapp.models.Users;

import java.util.ArrayList;

public class userHistoryFragment extends Fragment {

    private  userHistoryViewModel userHistoryViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        userHistoryViewModel =
                ViewModelProviders.of(this).get(userHistoryViewModel.class);
        View root = inflater.inflate(R.layout.user_history, container, false);

        ArrayList<Users> userList = new ArrayList<>();
        userList = userHistoryViewModel.getUsersList(getContext());


        return root;
    }
}
