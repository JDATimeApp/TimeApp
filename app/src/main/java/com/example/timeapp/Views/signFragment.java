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
import com.example.timeapp.ViewModels.signViewModel;

public class signFragment extends Fragment {

    private com.example.timeapp.ViewModels.signViewModel signViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signViewModel =
                ViewModelProviders.of(this).get(signViewModel.class);
        View root = inflater.inflate(R.layout.sign_fragment, container, false);
        return root;
    }
}
