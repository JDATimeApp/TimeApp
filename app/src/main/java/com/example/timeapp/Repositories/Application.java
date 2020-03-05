package com.example.timeapp.Repositories;

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