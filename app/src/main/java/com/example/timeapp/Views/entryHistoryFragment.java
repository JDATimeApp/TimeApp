package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.entryHistoryViewModel;

public class entryHistoryFragment extends Fragment {

    private entryHistoryViewModel entryHistoryViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        entryHistoryViewModel = ViewModelProviders.of(this).get(entryHistoryViewModel.class);
        View root = inflater.inflate(R.layout.entry_history, container, false);
        return root;
    }
}
