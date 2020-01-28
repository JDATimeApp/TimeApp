package com.example.timeapp.ViewModels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.models.Users;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerView extends RecyclerView.Adapter<UserRecyclerView.UserViewHolder> {

    private List<Users> usersList;

    public UserRecyclerView(List<Users> usersList){
        this.usersList = usersList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_history_row,viewGroup,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder uvh,int i){
        String username = usersList.get(i).getUsername();
        String email = usersList.get(i).getEmailAddress();

        uvh.ViewUsername.setText(username);
        uvh.ViewEmailAddress.setText(email);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView ViewUsername;
        public TextView ViewEmailAddress;

        public UserViewHolder(View user_history_row) {
            super(user_history_row);
            ViewUsername = user_history_row.findViewById(R.id.rv_txtViewUsername);
            ViewEmailAddress = user_history_row.findViewById(R.id.rv_txtViewEmail);
        }
    }
}