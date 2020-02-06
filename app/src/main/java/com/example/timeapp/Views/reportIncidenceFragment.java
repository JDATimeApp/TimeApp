package com.example.timeapp.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.ViewModels.ReportIncindenceViewModel;
import com.example.timeapp.models.Incidence;

public class reportIncidenceFragment extends Fragment {
    private ReportIncindenceViewModel reportIncindenceViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.report_incidence_fragment,container,false);
        reportIncindenceViewModel = ViewModelProviders.of(this).get(ReportIncindenceViewModel.class);
        final EditText subject= root.findViewById(R.id.messageSubject);
        final EditText message = root.findViewById(R.id.messageBody);
        Button reportIncidenceBtn= root.findViewById(R.id.btnReportIncidence);
        final SharedPreferences pref = getContext().getSharedPreferences("userInfo",0);
        reportIncidenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subject.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Subject must not be empty!",Toast.LENGTH_SHORT).show();
                } else if (message.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Message must not be empty!",Toast.LENGTH_SHORT).show();
                } else {
                    String userId = pref.getString("userId","");
                    reportIncindenceViewModel.reportIncidence(new Incidence(userId,
                            reportIncindenceViewModel.getUsernameById(userId,getContext()),
                            subject.getText().toString(),
                            message.getText().toString(),
                            Repository.getActualDay(Repository.getActualDateTime())));
                }
            }
        });
        return root;
    }

}
