package com.example.timeapp.ViewModels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.models.Incidence;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IncidenceHistoryViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Incidence>> incidenceList;
    private ArrayList<Incidence> incidences;

    public IncidenceHistoryViewModel(){
        incidenceList = new MutableLiveData<>();
        incidences = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<Incidence>> getIncidenceList() {return incidenceList;}

    public void getReportedIncidences(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference incidenceReference = firebaseDatabase.getReference().child("Incidences");

        incidenceReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    incidences.add(d.getValue(Incidence.class));
                    incidenceList.postValue(incidences);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("","Error while reading Firebase data");
            }
        });
    }
}
