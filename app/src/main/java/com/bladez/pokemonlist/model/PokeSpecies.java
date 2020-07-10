package com.bladez.pokemonlist.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Collections;


public class PokeSpecies implements Parcelable {

    private Integer base_happiness;
    private String capture_rate;
    private JsonElement color;
    private JsonArray egg_groups;
    private JsonElement evolution_chain;
    private JsonElement evolves_from_species;
    private JsonArray flavor_text_entries;
    private JsonArray form_descriptions;
    private Boolean form_switchable;
    private Integer gender_rate;
    private JsonArray genera;
    private JsonElement generation;
    private JsonElement growth_rate;
    private JsonElement habitat;
    private Boolean has_gender_differences;
    private Integer hatch_counter;
    private Integer id;
    private Boolean is_baby;
    private String name;
    private Integer order;
    private JsonArray pal_park_encounters;
    private JsonArray pokedex_numbers;
    private JsonElement shape;
    private JsonArray varieties;

    public PokeSpecies(Integer base_happiness, String capture_rate, JsonElement color, JsonArray egg_groups, JsonElement evolution_chain, JsonElement evolves_from_species, JsonArray flavor_text_entries, JsonArray form_descriptions, Boolean form_switchable, Integer gender_rate, JsonArray genera, JsonElement generation, JsonElement growth_rate, JsonElement habitat, Boolean has_gender_differences, Integer hatch_counter, Integer id, Boolean is_baby, String name, Integer order, JsonArray pal_park_encounters, JsonArray pokedex_numbers, JsonElement shape, JsonArray varieties) {
        this.base_happiness = base_happiness;
        this.capture_rate = capture_rate;
        this.color = color;
        this.egg_groups = egg_groups;
        this.evolution_chain = evolution_chain;
        this.evolves_from_species = evolves_from_species;
        this.flavor_text_entries = flavor_text_entries;
        this.form_descriptions = form_descriptions;
        this.form_switchable = form_switchable;
        this.gender_rate = gender_rate;
        this.genera = genera;
        this.generation = generation;
        this.growth_rate = growth_rate;
        this.habitat = habitat;
        this.has_gender_differences = has_gender_differences;
        this.hatch_counter = hatch_counter;
        this.id = id;
        this.is_baby = is_baby;
        this.name = name;
        this.order = order;
        this.pal_park_encounters = pal_park_encounters;
        this.pokedex_numbers = pokedex_numbers;
        this.shape = shape;
        this.varieties = varieties;
    }

    protected PokeSpecies(Parcel in) {
        if (in.readByte() == 0) {
            base_happiness = null;
        } else {
            base_happiness = in.readInt();
        }
        capture_rate = in.readString();
        byte tmpForm_switchable = in.readByte();
        form_switchable = tmpForm_switchable == 0 ? null : tmpForm_switchable == 1;
        if (in.readByte() == 0) {
            gender_rate = null;
        } else {
            gender_rate = in.readInt();
        }
        byte tmpHas_gender_differences = in.readByte();
        has_gender_differences = tmpHas_gender_differences == 0 ? null : tmpHas_gender_differences == 1;
        if (in.readByte() == 0) {
            hatch_counter = null;
        } else {
            hatch_counter = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        byte tmpIs_baby = in.readByte();
        is_baby = tmpIs_baby == 0 ? null : tmpIs_baby == 1;
        name = in.readString();
        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }
    }

    public static final Creator<PokeSpecies> CREATOR = new Creator<PokeSpecies>() {
        @Override
        public PokeSpecies createFromParcel(Parcel in) {
            return new PokeSpecies(in);
        }

        @Override
        public PokeSpecies[] newArray(int size) {
            return new PokeSpecies[size];
        }
    };

    public Integer getBase_happiness() {
        return base_happiness;
    }

    public void setBase_happiness(Integer base_happiness) {
        this.base_happiness = base_happiness;
    }

    public String getCapture_rate() {
        return capture_rate;
    }

    public void setCapture_rate(String capture_rate) {
        this.capture_rate = capture_rate;
    }

    public JsonElement getColor() {
        return color;
    }

    public void setColor(JsonElement color) {
        this.color = color;
    }

    public JsonArray getEgg_groups() {
        return egg_groups;
    }

    public void setEgg_groups(JsonArray egg_groups) {
        this.egg_groups = egg_groups;
    }

    public JsonElement getEvolution_chain() {
        return evolution_chain;
    }

    public void setEvolution_chain(JsonElement evolution_chain) {
        this.evolution_chain = evolution_chain;
    }

    public JsonElement getEvolves_from_species() {
        return evolves_from_species;
    }

    public void setEvolves_from_species(JsonElement evolves_from_species) {
        this.evolves_from_species = evolves_from_species;
    }

    public JsonArray getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(JsonArray flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public JsonArray getForm_descriptions() {
        return form_descriptions;
    }

    public void setForm_descriptions(JsonArray form_descriptions) {
        this.form_descriptions = form_descriptions;
    }

    public Boolean getForm_switchable() {
        return form_switchable;
    }

    public void setForm_switchable(Boolean form_switchable) {
        this.form_switchable = form_switchable;
    }

    public Integer getGender_rate() {
        return gender_rate;
    }

    public void setGender_rate(Integer gender_rate) {
        this.gender_rate = gender_rate;
    }

    public JsonArray getGenera() {
        return genera;
    }

    public void setGenera(JsonArray genera) {
        this.genera = genera;
    }

    public JsonElement getGeneration() {
        return generation;
    }

    public void setGeneration(JsonElement generation) {
        this.generation = generation;
    }

    public JsonElement getGrowth_rate() {
        return growth_rate;
    }

    public void setGrowth_rate(JsonElement growth_rate) {
        this.growth_rate = growth_rate;
    }

    public JsonElement getHabitat() {
        return habitat;
    }

    public void setHabitat(JsonElement habitat) {
        this.habitat = habitat;
    }

    public Boolean getHas_gender_differences() {
        return has_gender_differences;
    }

    public void setHas_gender_differences(Boolean has_gender_differences) {
        this.has_gender_differences = has_gender_differences;
    }

    public Integer getHatch_counter() {
        return hatch_counter;
    }

    public void setHatch_counter(Integer hatch_counter) {
        this.hatch_counter = hatch_counter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_baby() {
        return is_baby;
    }

    public void setIs_baby(Boolean is_baby) {
        this.is_baby = is_baby;
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

    public JsonArray getPal_park_encounters() {
        return pal_park_encounters;
    }

    public void setPal_park_encounters(JsonArray pal_park_encounters) {
        this.pal_park_encounters = pal_park_encounters;
    }

    public JsonArray getPokedex_numbers() {
        return pokedex_numbers;
    }

    public void setPokedex_numbers(JsonArray pokedex_numbers) {
        this.pokedex_numbers = pokedex_numbers;
    }

    public JsonElement getShape() {
        return shape;
    }

    public void setShape(JsonElement shape) {
        this.shape = shape;
    }

    public JsonArray getVarieties() {
        return varieties;
    }

    public void setVarieties(JsonArray varieties) {
        this.varieties = varieties;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.capture_rate);
        dest.writeString(this.name);
        dest.writeList(Collections.singletonList(this.egg_groups));
        dest.writeList(Collections.singletonList(this.flavor_text_entries));
        dest.writeList(Collections.singletonList(this.form_descriptions));
        dest.writeList(Collections.singletonList(this.genera));
        dest.writeList(Collections.singletonList(this.pal_park_encounters));
        dest.writeList(Collections.singletonList(this.color));
        dest.writeList(Collections.singletonList(this.evolution_chain));
        dest.writeList(Collections.singletonList(this.evolves_from_species));
        dest.writeList(Collections.singletonList(this.generation));
        dest.writeList(Collections.singletonList(this.growth_rate));
        dest.writeList(Collections.singletonList(this.habitat));
        dest.writeList(Collections.singletonList(this.shape));
        dest.writeList(Collections.singletonList(this.pokedex_numbers));
        dest.writeInt(this.base_happiness);
        dest.writeInt(this.gender_rate);
        dest.writeInt(this.hatch_counter);
        dest.writeInt(this.id);
        dest.writeInt(this.order);
        dest.writeBoolean(this.form_switchable);
        dest.writeBoolean(this.is_baby);
    }

    @Override
    public String toString() {
        return "PokeSpecies{" +
                "base_happiness=" + base_happiness +
                ", capture_rate='" + capture_rate + '\'' +
                ", color=" + color +
                ", egg_groups=" + egg_groups +
                ", evolution_chain=" + evolution_chain +
                ", evolves_from_species=" + evolves_from_species +
                ", flavor_text_entries=" + flavor_text_entries +
                ", form_descriptions=" + form_descriptions +
                ", form_switchable=" + form_switchable +
                ", gender_rate=" + gender_rate +
                ", genera=" + genera +
                ", generation=" + generation +
                ", growth_rate=" + growth_rate +
                ", habitat=" + habitat +
                ", has_gender_differences=" + has_gender_differences +
                ", hatch_counter=" + hatch_counter +
                ", id=" + id +
                ", is_baby=" + is_baby +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", pal_park_encounters=" + pal_park_encounters +
                ", pokedex_numbers=" + pokedex_numbers +
                ", shape=" + shape +
                ", varieties=" + varieties +
                '}';
    }
}
