package com.example.timeapp.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.models.Incidence;

import java.util.ArrayList;

public class IncidenceRecyclerView extends RecyclerView.Adapter<IncidenceRecyclerView.IncidenceViewHolder>{

    private ArrayList<Incidence> incidences;

    public IncidenceRecyclerView(ArrayList<Incidence> incidences){this.incidences = incidences;}

    public void setIncidences(ArrayList<Incidence> incidences) {
        this.incidences = incidences;
    }

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
            Username = row.findViewById(R.id.txtUsername);
            Subject = row.findViewById(R.id.txtSubject);
            Date = row.findViewById(R.id.txtDate);
        }
    }
}
