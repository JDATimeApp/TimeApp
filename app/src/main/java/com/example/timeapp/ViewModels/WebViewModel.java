package com.example.timeapp.ViewModels;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebViewModel extends ViewModel {

    private MutableLiveData<String> mWeb;

    public WebViewModel() {
        mWeb = new MutableLiveData<>();
    }

    public MutableLiveData<String> getWeb() {
        return mWeb;
    }

    public void downloadURL(String web){
        HttpURLConnection connection;
        URL url;
        String result = "";

        try{
            url = new URL(web);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            int data = inputStream.read();


            while (data != -1){
                result += (char) data;
                data = inputStream.read();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mWeb.postValue(result);
    }
}