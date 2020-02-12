package com.example.timeapp.ViewModels;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import com.example.timeapp.Repositories.Repository;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        Boolean t = Repository.checkIfUserIsRegistered(email,username,c);
        if (t == true){
            return true; // Returns true the user is already registered
         } else {
            Repository.registerNewUser(email,username,password,c); // If there's no an equal username , then register it
            return false;
        }
    }

    public static class insertUserTask extends AsyncTask<Void,Void,Boolean> {
        private String username;
        private String email;
        private String password;
        private Context context;

        public insertUserTask(String username,String email,String password,Context c){
            this.username = username;
            this.email = email;
            this.password = password;
            this.context = c;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Connection c = Repository.openPostgresConnection();
            Boolean output = false;

            if (c != null){ // If there is connection insert in postgreSQL
                String insertUser = "INSERT INTO users(username,password,emailaddress) VALUES(?,?,?);";
                String usernameIsRegistered = "SELECT * FROM users WHERE (username= ?) or (emailaddress=?)";

                PreparedStatement Validation;
                PreparedStatement insertNewUser;

                try {
                    Validation = c.prepareStatement(usernameIsRegistered);
                    Validation.setString(1,username);
                    Validation.setString(2,email);

                    ResultSet rst = Validation.executeQuery();

                    if (rst.next() == false){
                        insertNewUser = c.prepareStatement(insertUser);

                        // Passing values to insert new username

                        insertNewUser.setString(1,username);
                        insertNewUser.setString(2,password);
                        insertNewUser.setString(3,email);

                        insertNewUser.executeUpdate(); // Executing the query

                        Log.d("PostgreSQL","ResultSet is empty");
                        output = true;

                    } else {
                        Log.d("PostgreSQL","ResultSet have information");
                        output = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else { // Else if not , insert in LiteSQL
                if ((Repository.checkIfUserIsRegistered(email,username,context)) == false){
                    Repository.registerNewUser(email,username,password,context);
                    Log.d("PostgreSQL","User "+username+" has been registered - LiteSQL");
                    output = true;

                } else {
                    Log.d("PostgreSQL","User "+username+" is already registered - LiteSQL");
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

            return output;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                Toast.makeText(context,"User successfully registered!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,"User is already registered!",Toast.LENGTH_LONG).show();
            }
        }
    }
}