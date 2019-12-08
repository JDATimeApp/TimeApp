package com.example.timeapp.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.ViewModels.UserRecyclerView;
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
        // Reference to RecyclerView
        RecyclerView rv = root.findViewById(R.id.rv_userHistory);
        // Set the LayoutManager
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        // Get the data
        ArrayList<Users> usersList;
        usersList = userHistoryViewModel.getUsersList(getContext());
        //Set the adapter
        UserRecyclerView urv = new UserRecyclerView(usersList);
        rv.setAdapter(urv);
        return root;
    }
}
