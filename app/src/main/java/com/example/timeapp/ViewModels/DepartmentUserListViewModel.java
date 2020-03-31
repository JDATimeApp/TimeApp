package com.example.timeapp.ViewModels;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeapp.models.Users;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class DepartmentUserListViewModel extends ViewModel {

    public static ArrayList<Users> parseJson(String JSON){
        ArrayList<Users> users = new ArrayList<>();

        try {
            if (!JSON.isEmpty()){
                JSONObject obj = new JSONObject(JSON);

                // Getting each key value of all the object in the JSON
                Iterator<String> keys = obj.keys();

                while (keys.hasNext()){
                    String key = keys.next();
                    // If the child is a JSONObject we retrieve the data
                    if (obj.get(key) instanceof JSONObject ){
                        JSONObject actualUser = new JSONObject(obj.get(key).toString());
                        users.add(new Users(
                                actualUser.getString("emailAddress"),actualUser.getString("username"),actualUser.getString("password")
                        ));
                    }

                }
            }
        } catch (JSONException e) {
            Log.e("JSON","Malformed JSON!");
        }

        return users;

    }

    public static class GetDepartmentUsers extends AsyncTask<Void,Void,ArrayList<Users>>{

        private String department;
        private String page = "https://timeapp-73642.firebaseio.com/Departments/";

        private URL url;
        HttpURLConnection httpURLConnection;

        private MutableLiveData<ArrayList<Users>> userList;

        public MutableLiveData<ArrayList<Users>> getUserList() {
            return userList;
        }

        public GetDepartmentUsers(String department){
            this.department = department;
            userList = new MutableLiveData<>();
        }

        @Override
        protected ArrayList<Users> doInBackground(Void... voids) {
            URL url;
            HttpURLConnection httpURLConnection;
            ArrayList<Users> users = new ArrayList<>();

            try {

                page += department+".json";

                url = new URL(page);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                // If the connection is successful we get the data

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    String json = "";
                    StringBuilder response = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    while ((json = br.readLine()) != null) {
                        response.append(json+"\n");
                    }

                    Log.d("JSONUsers",response.toString());

                    //Parsing JSON to Users ArrayList
                    users = DepartmentUserListViewModel.parseJson(response.toString());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return users;
        }

        @Override
        protected void onPostExecute(ArrayList<Users> users) {
            super.onPostExecute(users);
            userList.postValue(users);
        }
    }

}
