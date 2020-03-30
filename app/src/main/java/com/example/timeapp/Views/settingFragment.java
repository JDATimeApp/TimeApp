package com.example.timeapp.Views;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeapp.R;
import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.ViewModels.settingViewModel;

public class settingFragment extends Fragment {

    ImageButton play;

    private settingViewModel settingsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.nav_settings, container, false);
        play = root.findViewById(R.id.settings);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Repository.playamorbillie()) {
                    play.setImageResource(R.drawable.ic_volume_off);
                } else {
                    play.setImageResource(R.drawable.ic_volume);

                }
            }
        });

        return root;

    }
}
