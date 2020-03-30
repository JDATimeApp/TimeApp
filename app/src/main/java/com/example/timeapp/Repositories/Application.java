package com.example.timeapp.Repositories;

import android.media.MediaPlayer;

import com.example.timeapp.R;

public class Application extends android.app.Application {

    @Override
    public void onCreate() { // Get the repository when opening the app
        super.onCreate();
        Repository.get(this);
        Repository.playamorbillie();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}