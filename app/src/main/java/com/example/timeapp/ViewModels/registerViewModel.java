package com.example.timeapp.ViewModels;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.example.timeapp.Repositories.Repository;
import java.io.FileNotFoundException;

public class registerViewModel extends ViewModel {

    /*public boolean registerNewUser(String username, String passwd, String email, Context c) {

            Boolean t = Repository.registerNewUser(username, passwd, email, c);

            if (!t) {
                return false;
            }
            return true;
    }
*/
    public boolean checkIfUserIsRegistered(String email,String username,String password,Context c){
        Boolean t = Repository.checkIfUserIsRegistered(email,username,password,c);
        if (t == true){
            return true; // Returns true the user is already registered
         } else {
            Repository.registerNewUser(email,username,password,c); // If there's no an equal username , then register it
            return false;
        }
    }
}