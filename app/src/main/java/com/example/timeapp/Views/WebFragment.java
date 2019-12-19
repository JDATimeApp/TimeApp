package com.example.timeapp.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.timeapp.R;
import com.example.timeapp.ViewModels.WebViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebFragment extends Fragment {

    private WebViewModel webViewModel;
    private WebView webView;
    private  URL WEB_URL;
    //"https://agora.xtec.cat/insjoandaustria/"
    private HttpURLConnection connection;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        webViewModel =
                ViewModelProviders.of(this).get(WebViewModel.class);
        View root = inflater.inflate(R.layout.web_fragment, container, false);

        webView = root.findViewById(R.id.webView);

        return root;
    }
}
