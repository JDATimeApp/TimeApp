package com.example.timeapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.timeapp.R;
import com.google.android.material.navigation.NavigationView;

public class AdminMainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_activity);
        DrawerLayout drawer = findViewById(R.id.drawer_adminlayout);
        NavigationView navigationView = findViewById(R.id.nav_adminview);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_adminHistory)
                .setDrawerLayout(drawer).build();

        NavController navController = Navigation.findNavController(this,R.id.nav_host_adminfragment);
        //NavigationUI.setupActionBarWithNavController(this,navController,mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);

    }
}