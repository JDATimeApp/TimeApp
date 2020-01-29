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
import com.example.timeapp.ViewModels.ReportIncindenceViewModel;

public class reportIncidenceFragment extends Fragment {
    private ReportIncindenceViewModel reportIncindenceViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.report_incidence_fragment,container,false);
        reportIncindenceViewModel = ViewModelProviders.of(this).get(ReportIncindenceViewModel.class);
        return root;
    }

}
