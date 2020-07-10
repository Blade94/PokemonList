package com.bladez.pokemonlist.ui.component.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bladez.pokemonlist.R;
import com.bladez.pokemonlist.model.PokeDetails;
import com.bladez.pokemonlist.ui.component.holder.PokeItemHolder;
import com.bladez.pokemonlist.model.PokeItem;
import com.bladez.pokemonlist.service.PokeAsyncTask;
import com.bladez.pokemonlist.service.PokeCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PokeItemAdapter extends RecyclerView.Adapter {

    private ArrayList<PokeItem> pokeItems;
    private ContextWrapper contextWrapper;
    private String next;
    private PokeCallback detailsCallback;

    private PokeCallback callback = res -> {
        if (res != null) {
            try {
                Gson gson = new Gson();
                JSONObject jsonResponse = new JSONObject(res);
                next = jsonResponse.getString("next");
    //            next = null;

                Type listType = new TypeToken<List<PokeItem>>(){}.getType();
                ArrayList<PokeItem> pokemons = gson.fromJson(jsonResponse.getJSONArray("results").toString(), listType);

                pokeItems.addAll(pokemons);

                notifyDataSetChanged();

//                Log.d("LOG", "next = " + next);
//                Log.d("LOG", "pokemons = " + pokemons.toString());

            } catch (JSONException e) {
                //TODO error
                e.printStackTrace();
            }
        } else {
            Toast.makeText(contextWrapper, "Pokemon not found!", Toast.LENGTH_SHORT).show();
        }
    };

    public PokeItemAdapter(ContextWrapper contextWrapper, ArrayList<PokeItem> pokeItems, String next, PokeCallback detailsCallback) {
        this.contextWrapper = contextWrapper;
        this.pokeItems = pokeItems;
        this.next = next;
        this. detailsCallback = detailsCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poke_item, parent, false);
        return new PokeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PokeItem item = pokeItems.get(position);
        PokeItemHolder pokeItemHolder = (PokeItemHolder) holder;
        pokeItemHolder.getName().setText(item.getName().substring(0, 1).toUpperCase() + item.getName().substring(1));
        pokeItemHolder.setUrl(item.getUrl());

        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/" + (position+1) + ".png").fit().into(pokeItemHolder.getSprite());

        pokeItemHolder.itemView.setOnClickListener(v -> {
            //TODO download pokedetails and pass to next view
            PokeAsyncTask task = new PokeAsyncTask(pokeItemHolder.getUrl().split("/v2/")[1], detailsCallback);
            task.execute();
        });

        if (position == pokeItems.size() - 50) {
//            Log.d("LOG", "You are getting closer to the end!");
            if (next != null) {
                String[] query = next.split("/v2/");
                if (query.length == 2) {
                    PokeAsyncTask task = new PokeAsyncTask(query[1], callback);
                    task.execute();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return pokeItems.size();
    }

}
