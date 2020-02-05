package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.IncidenceHistoryViewModel;

import java.util.zip.Inflater;

public class IncidenceHistoryFragment extends Fragment {

    private IncidenceHistoryViewModel incidenceHistoryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.incidence_history,container,false);
        incidenceHistoryViewModel = ViewModelProviders.of(this).get(IncidenceHistoryViewModel.class);

        RecyclerView rv = root.findViewById(R.id.rvIncidences);

        return root;
    }
}
