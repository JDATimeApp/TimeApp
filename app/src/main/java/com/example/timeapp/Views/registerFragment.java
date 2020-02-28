package com.example.timeapp.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.ViewModels.registerViewModel;
import com.example.timeapp.models.Users;

import java.sql.Connection;
import java.sql.SQLException;

public class registerFragment extends Fragment {

    private registerViewModel registerViewModel;

    private EditText editTxtEmail;
    private EditText editTxtUsername;
    private EditText editTxtPassword;
    private Button btnRegister;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        registerViewModel =
                ViewModelProviders.of(this).get(registerViewModel.class);

        View root = inflater.inflate(R.layout.register_fragment, container, false);
        editTxtEmail = root.findViewById(R.id.txtEditEmailAddress);
        editTxtUsername = root.findViewById(R.id.txtEditUsername);
        editTxtPassword = root.findViewById(R.id.txtEditPassword);
        btnRegister = root.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Repository.playOofSound();

                registerViewModel.insertUserTask insertUserTask = new registerViewModel.insertUserTask(
                        editTxtUsername.getText().toString(),
                        editTxtEmail.getText().toString(),
                        editTxtPassword.getText().toString(),getContext());

                insertUserTask.execute();
                Log.d("Click","clickado");

            }
        });
        return root;
    }
}