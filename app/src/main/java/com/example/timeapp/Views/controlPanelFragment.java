package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.ControlPanelViewModel;
import com.example.timeapp.ViewModels.DepartmentRecyclerView;

import java.util.ArrayList;

public class controlPanelFragment extends Fragment {

    private ControlPanelViewModel controlPanelViewModel;
    private RecyclerView departmentsRV;
    private ArrayList<String> departmentList = new ArrayList<>();
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

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        departmentsRV.setLayoutManager(llm);

        final DepartmentRecyclerView adapter = new DepartmentRecyclerView(departmentList, Navigation.findNavController(getActivity(),R.id.nav_host_adminfragment));
        controlPanelViewModel.getDepartments();

        controlPanelViewModel.getDepartmentList().observe(this,
                new Observer<ArrayList<String>>() {
                    @Override
                    public void onChanged(ArrayList<String> strings) {
                        departmentList = strings;
                        adapter.setDepartments(departmentList);
                        adapter.notifyDataSetChanged();
                    }
                });

        departmentList.clear();
        departmentsRV.setAdapter(adapter);

        addDepartmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlPanelViewModel.addDepartment(departmentNameTxt.getText().toString(),getFragmentManager());
            }
        });


        return root;
    }
}
