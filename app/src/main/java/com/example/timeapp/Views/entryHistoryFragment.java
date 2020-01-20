package com.example.timeapp.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.EntryRecyclerView;
import com.example.timeapp.ViewModels.entryHistoryViewModel;
import com.example.timeapp.models.Entry;

import java.util.ArrayList;

public class entryHistoryFragment extends Fragment {

    private entryHistoryViewModel entryHistoryViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        entryHistoryViewModel = ViewModelProviders.of(this).get(entryHistoryViewModel.class);
        View root = inflater.inflate(R.layout.entry_history, container, false);
        SharedPreferences pref = getContext().getSharedPreferences("userInfo",0);
        final String userId = pref.getString("userId","");
        RecyclerView rv = root.findViewById(R.id.rv_entryHistory);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Entry> entryList;
        entryList = entryHistoryViewModel.getUserEntries(userId,getContext());
        EntryRecyclerView erv = new EntryRecyclerView(entryList);
        rv.setAdapter(erv);

        return root;
    }
}
