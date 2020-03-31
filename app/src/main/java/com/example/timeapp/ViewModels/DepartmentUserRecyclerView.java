package com.example.timeapp.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.models.Users;

import java.util.ArrayList;

public class DepartmentUserRecyclerView extends RecyclerView.Adapter<DepartmentUserRecyclerView.DepartmentUserViewHolder>{

    private ArrayList<Users> users;

    public DepartmentUserRecyclerView(ArrayList<Users> users){
        this.users = users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    @Override
    public DepartmentUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_userlist_row,parent,false);
        return new DepartmentUserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentUserViewHolder holder, int position) {
        holder.username.setText(users.get(position).getUsername());
        holder.email.setText(users.get(position).getEmailAddress());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class DepartmentUserViewHolder extends RecyclerView.ViewHolder{

        private TextView username,email;

        public DepartmentUserViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.txtViewUsername);
            email = itemView.findViewById(R.id.txtViewEmail);

        }
    }

}
