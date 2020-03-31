package com.example.timeapp.ViewModels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;

import java.util.ArrayList;

public class DepartmentRecyclerView extends RecyclerView.Adapter<DepartmentRecyclerView.DepartmentViewHolder>{

    private ArrayList<String> departments;
    private NavController nv;

    public DepartmentRecyclerView(ArrayList<String> d , NavController nv){
        this.departments = d;
        this.nv = nv;
    }

    public void setDepartments(ArrayList<String> departments) {
        this.departments = departments;
    }

    @Override
    public DepartmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_row,parent,false);
        return new DepartmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DepartmentViewHolder holder, int position) {
        holder.departmentName.setText(departments.get(position));
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    public class DepartmentViewHolder extends RecyclerView.ViewHolder{

        private TextView departmentName;

        public DepartmentViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle b = new Bundle();
                    b.putString("department",departments.get(getAdapterPosition()));
                    nv.navigate(R.id.nav_departmentUserList,b);
                }
            });

            departmentName = itemView.findViewById(R.id.departmentNameTxt);
        }
    }

}
