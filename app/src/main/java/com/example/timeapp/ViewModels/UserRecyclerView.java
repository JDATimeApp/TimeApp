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

public class UserRecyclerView extends RecyclerView.Adapter<UserRecyclerView.AdapterHistory> {

    static List<Users> usersList;
    static NavController nv;

    public UserRecyclerView(List<Users> usersList, NavController nv){
        this.usersList = usersList;
        this.nv = nv;
    }


    public static class AdapterHistory extends RecyclerView.ViewHolder {

        TextView ViewEnterHour;
        TextView ViewLeaveHour;

        public AdapterHistory(final View user_row_history) {
            super(user_row_history);
            ViewEnterHour = user_row_history.findViewById(R.id.txtViewEnterHour);
            ViewLeaveHour = user_row_history.findViewById(R.id.txtViewLeaveHour);

        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    @Override
    public AdapterHistory onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_history_row,viewGroup,false);
        AdapterHistory ah = new AdapterHistory(v);
        return ah;
    }

    @Override
    public void onBindViewHolder(AdapterHistory adapterHistory,int i){
        adapterHistory.ViewEnterHour.setText(usersList.get(i).toString());
        adapterHistory.ViewLeaveHour.setText(usersList.get(i).toString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rv){
        super.onAttachedToRecyclerView(rv);
    }

}