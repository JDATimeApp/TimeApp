package com.example.timeapp.ViewModels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.Views.incidenceInfo;
import com.example.timeapp.models.Incidence;

import java.util.ArrayList;

public class IncidenceRecyclerView extends RecyclerView.Adapter<IncidenceRecyclerView.IncidenceViewHolder>{

    private ArrayList<Incidence> incidences;
    private NavController nc;

    public IncidenceRecyclerView(ArrayList<Incidence> incidences,NavController nc ){this.incidences = incidences; this.nc = nc;}
    public void setIncidences(ArrayList<Incidence> incidences) {
            this.incidences = incidences;
    }
    public ArrayList<Incidence> getIncidences() {return incidences;}

    @Override
    public IncidenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.incidence_history_row,parent,false);
        return new IncidenceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IncidenceViewHolder holder, int position) {

        holder.Username.setText(incidences.get(position).getUsername());
        holder.Subject.setText(incidences.get(position).getSubject());
        holder.Date.setText(incidences.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return incidences.size();
    }

    public class IncidenceViewHolder extends RecyclerView.ViewHolder{

        public TextView Username,Subject,Date;

        public IncidenceViewHolder(View row) {
            super(row);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle incidenceArgs = new Bundle();
                    incidenceArgs.putString("Date",incidences.get(getAdapterPosition()).getDate());
                    incidenceArgs.putString("Subject",incidences.get(getAdapterPosition()).getSubject());
                    incidenceArgs.putString("Message",incidences.get(getAdapterPosition()).getMessage());
                    incidenceArgs.putString("Username",incidences.get(getAdapterPosition()).getUsername());

                    nc.navigate(R.id.incidence_info,incidenceArgs);
                }
            });

            Username = row.findViewById(R.id.txtUsername);
            Subject = row.findViewById(R.id.txtSubject);
            Date = row.findViewById(R.id.txtDate);
        }
    }
}
