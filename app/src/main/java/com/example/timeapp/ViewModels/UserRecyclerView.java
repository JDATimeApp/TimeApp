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

import java.util.List;

public class UserRecyclerView extends RecyclerView.Adapter<UserRecyclerView.UserViewHolder> {

    static List<Users> usersList;

    public UserRecyclerView(List<Users> usersList){
        this.usersList = usersList;
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView ViewUsername;
        TextView ViewEmailAddress;

        public UserViewHolder(final View user_row_history) {
            super(user_row_history);
            ViewUsername = user_row_history.findViewById(R.id.txtViewUsername);
            ViewEmailAddress = user_row_history.findViewById(R.id.txtEditEmailAddress);

        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_history_row,viewGroup,false);
        UserViewHolder ah = new UserViewHolder(v);
        return ah;
    }

    @Override
    public void onBindViewHolder(UserViewHolder adapterHistory,int i){
        adapterHistory.ViewUsername.setText(usersList.get(i).toString());
        adapterHistory.ViewEmailAddress.setText(usersList.get(i).toString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rv){
        super.onAttachedToRecyclerView(rv);
    }

}