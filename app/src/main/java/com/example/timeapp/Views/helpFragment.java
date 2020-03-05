package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.HelpViewModel;


public class helpFragment extends Fragment {

    private HelpViewModel helpViewModel;
    private VideoView video;
    private MediaController mediaController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        helpViewModel =
                ViewModelProviders.of(this).get(HelpViewModel.class);

        View root = inflater.inflate(R.layout.fragment_help, container, false);

        video = root.findViewById(R.id.helpVideo);

        video.setVideoPath("android.resource://"+
                getContext().getPackageName()+"/"+R.raw.video1);

        mediaController = new MediaController(getContext());

        video.setMediaController(mediaController);

        mediaController.setAnchorView(video);
        mediaController.setMediaPlayer(video);

        return root;
    }
}
