package com.bladez.pokemonlist.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bladez.pokemonlist.R;
import com.bladez.pokemonlist.model.PokeDetails;
import com.bladez.pokemonlist.model.PokeItem;
import com.bladez.pokemonlist.service.PokeAsyncTask;
import com.bladez.pokemonlist.service.PokeCallback;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoadingFragment extends Fragment {

    private NavController navController;

    private PokeCallback callback = res -> {
        if (res != null) {
            try {

                Gson gson = new Gson();
                JSONObject jsonResponse = new JSONObject(res);
                String next = jsonResponse.getString("next");

                Type listType = new TypeToken<List<PokeItem>>(){}.getType();
                ArrayList<PokeItem> pokemons = gson.fromJson(jsonResponse.getJSONArray("results").toString(), listType);

                Bundle bundle = new Bundle();
                bundle.putString("next", next);
                bundle.putParcelableArrayList("pokemons", pokemons);

//                Log.d("LOG", "next = " + next);
//                Log.d("LOG", "pokemons = " + pokemons.toString());

                navController.navigate(R.id.listFragment, bundle);

            } catch (JSONException e) {
                //TODO error
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Pokemon not found!", Toast.LENGTH_SHORT).show();
        }
    };

    public LoadingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loading, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            PokeAsyncTask task = new PokeAsyncTask("pokemon-species?limit=100", callback);
            task.execute();
        }, 3000);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
