package com.example.timeapp.Views;

import android.content.SharedPreferences;
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
import com.example.timeapp.ViewModels.ReportIncindenceViewModel;
import com.example.timeapp.models.Incidence;

public class reportIncidenceFragment extends Fragment {
    private ReportIncindenceViewModel reportIncindenceViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.report_incidence_fragment,container,false);
        reportIncindenceViewModel = ViewModelProviders.of(this).get(ReportIncindenceViewModel.class);
        final EditText message= root.findViewById(R.id.incidenceMessage);
        Button reportIncidenceBtn= root.findViewById(R.id.btnReportIncidence);

        reportIncidenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Incidence must not be empty!",Toast.LENGTH_SHORT).show();
                    Log.d("Incidence","Incidence text is empty!");
                } else {
                    SharedPreferences pref = getContext().getSharedPreferences("userInfo",0);
                    String userId = pref.getString("userId","");
                    reportIncindenceViewModel.reportIncidence(new Incidence(userId,
                            message.getText().toString(),
                            Repository.getActualDateTime()));
                    Toast.makeText(getContext(),"Incidence reported successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

}
