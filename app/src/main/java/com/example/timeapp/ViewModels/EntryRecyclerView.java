package com.example.timeapp.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRecyclerView extends RecyclerView.Adapter<EntryRecyclerView.EntryViewHolder> {

    private List<Entry> entryList;

    public EntryRecyclerView(ArrayList<Entry> entryList){this.entryList = entryList;}

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.entry_history_row,viewGroup,false);
        return new EntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder uvh,int i){
        String entryDate = entryList.get(i).getEntryDate();
        String entryTime = entryList.get(i).getEntryTime();
        String leaveTime = entryList.get(i).getLeaveTime();

        uvh.entryDate.setText(entryDate);
        uvh.entryTime.setText(entryTime);
        uvh.leaveTime.setText(leaveTime);
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {

        public TextView entryDate,entryTime,leaveTime;

        public EntryViewHolder(View entry_history_row){
            super(entry_history_row);
            entryDate = entry_history_row.findViewById(R.id.txtentryDate);
            entryTime = entry_history_row.findViewById(R.id.txtentryTime);
            leaveTime = entry_history_row.findViewById(R.id.leaveTime);
        }
    }
}
