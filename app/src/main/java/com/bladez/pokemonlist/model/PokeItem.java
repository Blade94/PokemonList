package com.bladez.pokemonlist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PokeItem implements Parcelable {

    private String name;
    private String url;

    public PokeItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public PokeItem(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PokeItem{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        url = in.readString();
   }

    public static final Parcelable.Creator<PokeItem> CREATOR = new Parcelable.Creator<PokeItem>() {
        public PokeItem createFromParcel(Parcel in) {
            return new PokeItem(in);
        }

        public PokeItem[] newArray(int size) {
            return new PokeItem[size];
        }
    };
}
