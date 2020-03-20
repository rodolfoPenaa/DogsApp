package com.userInteractionsAndAndroidResourse.dogsapp.model;

import com.google.firebase.Timestamp;

public class  FavoriteDog {

private String raza;
private String url;
private Timestamp timestamp;

    public FavoriteDog(String raza, String url, Timestamp timestamp) {
        this.raza = raza;
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
