package com.example.timeapp.Views;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timeapp.MainActivity;
import com.example.timeapp.R;


public class splashScreenActivity extends AppCompatActivity {
    Thread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000); // Thread will sleep for 3 secs

                    // After5 seconds change intent
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } catch (Exception e){
                    Log.e("Splash","Error witch splashscreen");
                }
            }
        });
        thread.start(); // Starting the thread
    }
}
