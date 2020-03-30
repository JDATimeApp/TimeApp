package com.example.timeapp.ViewModels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.example.timeapp.UserMainActivity;
import com.google.firebase.database.core.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginViewModel extends ViewModel {

    public static boolean checkLogin(String username, String password, Context c){
        Boolean result = Repository.checkLogin(username,password,c);
        if (result == true){
            return true;
        } else {
            return false;
        }
    }

    public static String getUserId(String username,Context c){
        return Repository.getUserId(username,c);
    }

    public static class loginUserTask extends AsyncTask<Void,Void,Boolean> {
        private String username;
        private String password;
        private Context context;
        private String usernameShared = "" , idShared = "";

        private final MutableLiveData<Boolean> result = new MutableLiveData<>();

        public MutableLiveData<Boolean> getResult() {
            return result;
        }

        public loginUserTask(String username, String password, Context c) {
            this.username = username;
            this.password = password;
            this.context = c;

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Connection c = Repository.openPostgresConnection();
            Boolean output = false;

            if (c != null) {
                String usernameIslogin = "SELECT * FROM users WHERE username =? AND password =?";

                PreparedStatement Validation;

                try {
                    Validation = c.prepareStatement(usernameIslogin);
                    Validation.setString(1,username);
                    Validation.setString(2,password);

                    ResultSet rst = Validation.executeQuery();

                    if (!(rst.next() == false)) {

                        output = true;

                        String userIdQuery = "SELECT userid from Users where username = ?";
                        PreparedStatement user;

                        user = c.prepareStatement(userIdQuery);
                        user.setString(1,username);

                        ResultSet r = user.executeQuery();
                        if (r != null){
                            while (r.next()){
                                idShared = r.getString(1);
                                usernameShared = r.getString(2);
                            }
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                if ((Repository.checkLogin(username,password,context)) == true){
                    idShared = Repository.getUserId(username,context);
                    usernameShared = username;
                    Log.d("PostgreSQL","User "+username+" has been login - LiteSQL");
                    output = true;
                } else {
                    Log.d("PostgreSQL","User "+password+" is already login - LiteSQL");
                    output = false;
                }
            }

            if(c!=null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Once user is validated , ID and username is writed in SharedPreferences

            if (output){
                SharedPreferences pref = context.getSharedPreferences("userInfo",0);
                SharedPreferences.Editor ed = pref.edit();
                ed.putString("userId",idShared);
                ed.putString("username",usernameShared);
                ed.apply();
            }


            return output;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            result.postValue(aBoolean);
        }
    }
}