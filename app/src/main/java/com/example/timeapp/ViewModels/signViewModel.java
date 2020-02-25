package com.example.timeapp.ViewModels;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.Repositories.Repository;
import com.google.firebase.database.core.Repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signViewModel extends ViewModel {
    public static void setEntryTime(String userId, Context c){
        Repository.setEntryTime(userId,c);
    }

    public static void setLeaveTime(String userId,Context c){
        Repository.setLeaveTime(userId,c);
    }

    public static class signUserTask extends AsyncTask<Void,Void,Boolean> {
        private String userid;
        private Context context;
        private String description;

        private final MutableLiveData<Boolean> result = new MutableLiveData<>();

        public MutableLiveData<Boolean> getResult() {
            return result;
        }

        public signUserTask(String userid, String description, Context c) {
            this.userid = userid;
            this.description = description;
            this.context = c;

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Connection c = Repository.openPostgresConnection();
            Boolean output = false;

            if (c != null) {
                String entryValidation ="SELECT * FROM entries WHERE userid = ? and entrydate = ?";

                PreparedStatement Validation;
                try {
                    Validation = c.prepareStatement(entryValidation);

                    //Dia y la hora
                    Validation.setInt(1, Integer.parseInt(userid));
                    Validation.setString(2, Repository.getActualDay(Repository.getActualDateTime()));

                    ResultSet rst = Validation.executeQuery();

                    if (rst.next() == false) {

                        output = true;

                        String userIdQuery = "INSERT INTO entries(entryid, userid, entrydate, entrytime, description) VALUES (default, ?, ?, ?, ?)";
                        PreparedStatement insert;
                        insert = c.prepareStatement(userIdQuery);

                        insert.setInt(1,Integer.parseInt(userid));
                        insert.setString(2,Repository.getActualDay(Repository.getActualDateTime()));
                        insert.setString(3,Repository.getActualHour(Repository.getActualDateTime()));
                        insert.setString(4,description);

                        insert.executeQuery();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
