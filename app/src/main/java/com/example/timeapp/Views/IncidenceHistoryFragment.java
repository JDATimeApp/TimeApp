package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.IncidenceHistoryViewModel;
import com.example.timeapp.ViewModels.IncidenceRecyclerView;
import com.example.timeapp.models.Incidence;

import java.util.ArrayList;

public class IncidenceHistoryFragment extends Fragment {

    private IncidenceHistoryViewModel incidenceHistoryViewModel;
    private ArrayList<Incidence> incidences = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.incidence_history,container,false);
        incidenceHistoryViewModel = ViewModelProviders.of(this).get(IncidenceHistoryViewModel.class);
        RecyclerView rv = root.findViewById(R.id.rvIncidences);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        final IncidenceRecyclerView adapter = new IncidenceRecyclerView(incidences, Navigation.findNavController(getActivity(),R.id.nav_host_adminfragment));
        incidenceHistoryViewModel.getReportedIncidences();
        incidenceHistoryViewModel.getIncidenceList().observe(this,
                new Observer<ArrayList<Incidence>>() {
                    @Override
                    public void onChanged(ArrayList<Incidence> i) {
                        incidences = i;
                        adapter.setIncidences(incidences);
                        adapter.notifyDataSetChanged();
                    }
                });
        incidences.clear(); // Clearing the arrayList to prevent duplicate data
        rv.setAdapter(adapter);
        return root;
    }
}
