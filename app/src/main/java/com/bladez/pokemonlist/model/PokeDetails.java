package com.bladez.pokemonlist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Collections;

public class PokeDetails implements Parcelable {

    private JsonArray abilities;
    private String base_experience;
    private JsonArray forms;
    private JsonArray indices;
    private Integer height;
    private JsonArray held_items;
    private Integer id;
    private Boolean is_default;
    private String location_area_encounters;
    private JsonArray moves;
    private String name;
    private Integer order;
    private JsonObject species;
    private JsonObject sprites;
    private JsonArray stats;
    private JsonArray types;
    private Integer weight;

    public PokeDetails(JsonArray abilities, String base_experience, JsonArray forms, JsonArray indices, Integer height, JsonArray held_items, Integer id, Boolean is_default, String location_area_encounters, JsonArray moves, String name, Integer order, JsonObject species, JsonObject sprites, JsonArray stats, JsonArray types, Integer weight) {
        this.abilities = abilities;
        this.base_experience = base_experience;
        this.forms = forms;
        this.indices = indices;
        this.height = height;
        this.held_items = held_items;
        this.id = id;
        this.is_default = is_default;
        this.location_area_encounters = location_area_encounters;
        this.moves = moves;
        this.name = name;
        this.order = order;
        this.species = species;
        this.sprites = sprites;
        this.stats = stats;
        this.types = types;
        this.weight = weight;
    }

    protected PokeDetails(Parcel in) {
        base_experience = in.readString();
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        byte tmpIs_default = in.readByte();
        is_default = tmpIs_default == 0 ? null : tmpIs_default == 1;
        location_area_encounters = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }
        if (in.readByte() == 0) {
            weight = null;
        } else {
            weight = in.readInt();
        }
    }

    public static final Creator<PokeDetails> CREATOR = new Creator<PokeDetails>() {
        @Override
        public PokeDetails createFromParcel(Parcel in) {
            return new PokeDetails(in);
        }

        @Override
        public PokeDetails[] newArray(int size) {
            return new PokeDetails[size];
        }
    };

    public JsonArray getAbilities() {
        return abilities;
    }

    public void setAbilities(JsonArray abilities) {
        this.abilities = abilities;
    }

    public String getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(String base_experience) {
        this.base_experience = base_experience;
    }

    public JsonArray getForms() {
        return forms;
    }

    public void setForms(JsonArray forms) {
        this.forms = forms;
    }

    public JsonArray getIndices() {
        return indices;
    }

    public void setIndices(JsonArray indices) {
        this.indices = indices;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public JsonArray getHeld_items() {
        return held_items;
    }

    public void setHeld_items(JsonArray held_items) {
        this.held_items = held_items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_default() {
        return is_default;
    }

    public void setIs_default(Boolean is_default) {
        this.is_default = is_default;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public JsonArray getMoves() {
        return moves;
    }

    public void setMoves(JsonArray moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public JsonObject getSpecies() {
        return species;
    }

    public void setSpecies(JsonObject species) {
        this.species = species;
    }

    public JsonObject getSprites() {
        return sprites;
    }

    public void setSprites(JsonObject sprites) {
        this.sprites = sprites;
    }

    public JsonArray getStats() {
        return stats;
    }

    public void setStats(JsonArray stats) {
        this.stats = stats;
    }

    public JsonArray getTypes() {
        return types;
    }

    public void setTypes(JsonArray type) {
        this.types = types;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PokeDetails{" +
                "abilities=" + abilities +
                ", base_experience='" + base_experience + '\'' +
                ", forms=" + forms +
                ", indices=" + indices +
                ", height='" + height + '\'' +
                ", held_items=" + held_items +
                ", id=" + id +
                ", is_default=" + is_default +
                ", location_area_encounters='" + location_area_encounters + '\'' +
                ", moves=" + moves +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", species=" + species +
                ", sprites=" + sprites +
                ", stats=" + stats +
                ", types=" + types +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.base_experience);
        dest.writeString(this.location_area_encounters);
        dest.writeString(this.name);
        dest.writeList(Collections.singletonList(this.abilities));
        dest.writeList(Collections.singletonList(this.held_items));
        dest.writeList(Collections.singletonList(this.forms));
        dest.writeList(Collections.singletonList(this.indices));
        dest.writeList(Collections.singletonList(this.moves));
        dest.writeList(Collections.singletonList(this.species));
        dest.writeList(Collections.singletonList(this.sprites));
        dest.writeInt(this.id);
        dest.writeInt(this.order);
        dest.writeInt(this.weight);
        dest.writeInt(this.height);
    }
}
