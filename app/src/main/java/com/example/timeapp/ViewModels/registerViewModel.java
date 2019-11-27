package com.example.timeapp.ViewModels;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.example.timeapp.Repositories.Repository;
import java.io.FileNotFoundException;

public class registerViewModel extends ViewModel {

    public boolean registerNewUser(String username, String passwd, String email, Context c)  {

        try{
            Boolean t = Repository.registerNewUser(username,passwd,email,c);

            if (!t){
                return false;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return true;
    }
}