package com.example.timeapp.Views;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

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


import com.example.timeapp.ViewModels.LoginViewModel;
import com.example.timeapp.R;

public class loginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    EditText username, password;
    TextView remember;
    Button   login;
    TextView forgot_password1, linkRegister;
    TextView isAdmin;

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
                String us = username.getText().toString();
               Boolean t =loginViewModel.checkLogin(username.getText().toString(),
                       password.getText().toString(),
                       getContext());
               if (t == true){
                   SharedPreferences pref = getContext().getSharedPreferences("userInfo",0);
                   SharedPreferences.Editor ed = pref.edit();
                   String userId = loginViewModel.getUserId(us,getContext());
                   ed.putString("userId",userId).apply();
                   Fragment f = new signFragment();
                   FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                   ft.replace(R.id.fragment_container,f).commit();
                   //Creating the file for saving session user data when is validated
                   Toast.makeText(getContext(),"Welcome to the app",Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(getContext(),"You are not registered!",Toast.LENGTH_SHORT).show();
               }
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
                Fragment f = new userHistoryFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container,f);ft.addToBackStack(null).commit();
            }
        });
        return root;

    }
}
