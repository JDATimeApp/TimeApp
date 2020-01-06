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
import com.example.timeapp.ViewModels.registerViewModel;

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
                boolean t = registerViewModel.checkIfUserIsRegistered(editTxtEmail.getText().toString(),
                        editTxtUsername.getText().toString(),
                        editTxtPassword.getText().toString(),getContext());

                if(t == true){
                    Toast.makeText(getContext(),"That username is already registered!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),"Thank you for registering "+editTxtEmail.getText().toString(),Toast.LENGTH_SHORT).show();
                }
                Log.d("Click","clickado");
                // fragment_container is where the fragment is going to  be drawn
            }
        });
        return root;
    }
}