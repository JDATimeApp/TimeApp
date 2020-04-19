package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.DepartmentRecyclerView;
import com.example.timeapp.ViewModels.DepartmentUserListViewModel;
import com.example.timeapp.ViewModels.DepartmentUserRecyclerView;
import com.example.timeapp.models.Users;

import java.util.ArrayList;


public class DepartmentUserListFragment extends Fragment {

    private DepartmentUserListViewModel departmentUserListViewModel;
    private RecyclerView rv;
    private ArrayList<Users> userList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        departmentUserListViewModel = ViewModelProviders.of(this).get(DepartmentUserListViewModel.class);
        View root = inflater.inflate(R.layout.department_userlist,container,false);

        rv = root.findViewById(R.id.departmentUsersRv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null ){
            DepartmentUserListViewModel.GetDepartmentUsers users =
                    new DepartmentUserListViewModel.GetDepartmentUsers(getArguments().getString("department"));

            final DepartmentUserRecyclerView drv = new DepartmentUserRecyclerView(userList, Navigation.findNavController(getActivity(),R.id.nav_host_adminfragment),
                    getArguments().getString("department"));

            users.execute();

            users.getUserList().observe(this, new Observer<ArrayList<Users>>() {
                @Override
                public void onChanged(ArrayList<Users> users) {
                    userList = users;
                    drv.setUsers(userList);
                    drv.notifyDataSetChanged();
                }
            });

            rv.setAdapter(drv);
        }

        return root;
    }
}
