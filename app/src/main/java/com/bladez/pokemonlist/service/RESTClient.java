package com.bladez.pokemonlist.service;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RESTClient {

    private URL url;

    public RESTClient(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public String call() throws PokeException {
        StringBuilder response = new StringBuilder();

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new PokeException("StatusCode: " + connection.getResponseMessage());
            } else {
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String builder;
                while ((builder = br.readLine()) != null) {
                    response.append(builder);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Log.d("LOG", "response: " + response.toString());
        return response.toString();
    }

}
