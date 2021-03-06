package com.example.timeapp.Views;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.timeapp.AdminMainActivity;
import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.UserMainActivity;
import com.example.timeapp.ViewModels.LoginViewModel;
import com.example.timeapp.R;

public class loginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    EditText username, password;
    TextView remember;
    Button   login;
    TextView forgot_password1, linkRegister;
    TextView isAdmin;
    Boolean result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);

        View root = inflater.inflate(R.layout.login_fragment, container, false);
        username = root.findViewById(R.id.txtUser);
        password = root.findViewById(R.id.txtPassword);
        remember = root.findViewById(R.id.remember);
        login = root.findViewById(R.id.entrar);
        forgot_password1 = root.findViewById(R.id.forgot_password1);
        linkRegister = root.findViewById(R.id.linkRegister);
        isAdmin = root.findViewById(R.id.txtViewAdmin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Repository.playOofSound();

                LoginViewModel.loginUserTask loginUserTask = new LoginViewModel.loginUserTask(
                        username.getText().toString(),
                        password.getText().toString(),
                        getContext());

                loginUserTask.execute();

                loginUserTask.getResult().observe(getViewLifecycleOwner(),
                        new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {

                        if (aBoolean) {
                            Intent i = new Intent(getContext(), UserMainActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(getContext(), "User is not registered!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                loginUserTask.getResult();



            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Fragment fm = new registerFragment();
               FragmentTransaction transactionuno = getActivity().getSupportFragmentManager().beginTransaction();
               transactionuno.replace(R.id.fragment_container,fm);transactionuno.addToBackStack(null).commit();
            }
        });

        isAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AdminMainActivity.class);
                startActivity(i);
            }
        });
        return root;

    }
}
