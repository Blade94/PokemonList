package com.bladez.pokemonlist.service;

import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;

public class PokeAsyncTask extends AsyncTask<Void, Void, String> {

    private String endpoint;
    private PokeCallback callback;

    public PokeAsyncTask(String endpoint, PokeCallback callback) {
        this.endpoint = endpoint;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response = "";

        try {
            RESTClient client = new RESTClient("https://pokeapi.co/api/v2/" + endpoint);
            response = client.call();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (PokeException e) {
            e.printStackTrace();
            response = null;
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callback.onResult(s);
    }
}
