package com.example.timeapp.Views;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

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

import com.example.timeapp.Views.registerFragment;

import com.example.timeapp.ViewModels.LoginViewModel;
import com.example.timeapp.R;

public class loginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    public static loginFragment newInstance() {
        return new loginFragment();
    }

    EditText username, password;
    TextView remember;
    Button   login;
    TextView forgot_password1, linkRegister;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        username = root.findViewById(R.id.txtUser);
        password = root.findViewById(R.id.txtPassword);
        remember = root.findViewById(R.id.remember);
        login = root.findViewById(R.id.entrar);
        forgot_password1 = root.findViewById(R.id.forgot_password1);
        linkRegister = root.findViewById(R.id.linkRegister);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login.isLongClickable(username.getText().toString(), password.getText().toString());
            }
        });
        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Fragment fm = new registerFragment();
               FragmentTransaction transactionuno = getActivity().getSupportFragmentManager().beginTransaction();
               transactionuno.replace(R.id.fragment_container,fm);transactionuno.commit();
            }
        });

        return root;

    }
}
