package com.example.timeapp.Views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.timeapp.R;
import com.example.timeapp.ViewModels.WebViewModel;
import java.net.HttpURLConnection;

public class WebFragment extends Fragment {

    private WebViewModel webViewModel;
    private WebView webView;
    private final String WEB_URL ="https://www.york.ac.uk/teaching/cws/wws/webpage1.html";
    // https://www.york.ac.uk/teaching/cws/wws/webpage1.html
    private HttpURLConnection connection;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        webViewModel =
                ViewModelProviders.of(this).get(WebViewModel.class);
        View root = inflater.inflate(R.layout.web_fragment, container, false);

        webView = root.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        MyThread thread= new MyThread();
        thread.execute(WEB_URL);

        webViewModel.getWeb().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                webView.loadData(s,"text/html","utf-8");
            }
        });
        return root;
    }

    public class MyThread extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            webViewModel.downloadURL(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
