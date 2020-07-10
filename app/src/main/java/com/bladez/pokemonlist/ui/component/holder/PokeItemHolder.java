package com.bladez.pokemonlist.ui.component.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bladez.pokemonlist.R;

public class PokeItemHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private String url;
    private ImageView sprite;

    public PokeItemHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.txt_poke_name);
        sprite = itemView.findViewById(R.id.img_sprite);
    }

    public TextView getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageView getSprite() {
        return sprite;
    }
}
