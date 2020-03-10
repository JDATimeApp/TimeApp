package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.ControlPanelViewModel;

public class controlPanelFragment extends Fragment {

    private ControlPanelViewModel controlPanelViewModel;
    private RecyclerView departmentsRV;
    private EditText departmentNameTxt;
    private Button addDepartmentBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        controlPanelViewModel =
                ViewModelProviders.of(this).get(ControlPanelViewModel.class);

        View root = inflater.inflate(R.layout.control_panel,container,false);

        departmentNameTxt = root.findViewById(R.id.departmentNameEditTxt);
        departmentsRV = root.findViewById(R.id.departmentRV);
        addDepartmentBtn = root.findViewById(R.id.addDepartmentBtn);

        return root;
    }
}
