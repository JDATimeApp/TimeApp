package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.timeapp.R;

public class incidenceInfo extends Fragment {

    TextView subject,date,message,username;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.incidence_info,container,false);

        subject = root.findViewById(R.id.incidence_subject);
        username = root.findViewById(R.id.incidence_from);
        date = root.findViewById(R.id.incidence_date);
        message = root.findViewById(R.id.incidence_message);

        if (getArguments() != null){
            subject.setText(getArguments().getString("Subject"));
            date.setText(getArguments().getString("Date"));
            message.setText(getArguments().getString("Message"));
            username.setText(getArguments().getString("Username"));
        }
        return root;
    }
}
